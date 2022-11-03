package com.kkp.buddytrainer.core

import com.kkp.buddytrainer.domain.model.Exercise

sealed class Resource{
    class Success (val data : MutableList<Exercise>) : Resource()
    class Failure (val error : String) : Resource()
    object Loading : Resource()
    object Empty : Resource()

}
