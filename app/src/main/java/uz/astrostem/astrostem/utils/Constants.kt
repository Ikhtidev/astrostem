package uz.astrostem.astrostem.utils

import uz.astrostem.astrostem.database.entity.Theme

interface Constants {
    companion object {
        var FILE_NUMBER = 0
        var FRAGMENT_TYPE = TYPE.T
        val THEME_LIST: ArrayList<Theme> = ArrayList()
    }
}