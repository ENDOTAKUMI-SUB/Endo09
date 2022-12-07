package jp.ac.neec.it.k020c1094.endo09.data

import android.app.Application

/**
 * グローバル変数を定義
 *
 * @author ENDO Takumi
 */
class Money: Application() {
    private var money = 2000


    fun getMoneyInt(): Int? {
        return money
    }

    fun setMoneyInt(value: Int) {
        money = value
    }
}