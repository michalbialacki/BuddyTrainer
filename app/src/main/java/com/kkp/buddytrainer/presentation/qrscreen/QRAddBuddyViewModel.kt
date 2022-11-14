package com.kkp.buddytrainer.presentation.qrscreen

import androidx.lifecycle.ViewModel
import com.kkp.buddytrainer.domain.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class QRAddBuddyViewModel @Inject constructor(
    personRepository: PersonRepository
) : ViewModel(){


}