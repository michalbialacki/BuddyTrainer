package com.kkp.buddytrainer.di

import android.content.Context
import androidx.room.Room
import com.kkp.buddytrainer.core.Constants.PERSON_TABLE
import com.kkp.buddytrainer.data.network.PersonDAO
import com.kkp.buddytrainer.data.network.PersonDb
import com.kkp.buddytrainer.data.repository.PersonRepositoryImpl
import com.kkp.buddytrainer.domain.repository.PersonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {
    @Provides
    fun providePersonDb(
        @ApplicationContext
        context : Context
    ) = Room.databaseBuilder(
        context,
        PersonDb::class.java,
        PERSON_TABLE
    ).fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Provides
    fun providePersonDAO(
        personDb: PersonDb
    ) = personDb.personDAO()

    @Provides
    fun providePersonRepository(
        personDao: PersonDAO
    ) : PersonRepository = PersonRepositoryImpl(
        personDAO = personDao
    )

}