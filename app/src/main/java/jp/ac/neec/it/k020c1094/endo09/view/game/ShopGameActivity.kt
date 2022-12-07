package jp.ac.neec.it.k020c1094.endo09.view.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import jp.ac.neec.it.k020c1094.endo09.R
import jp.ac.neec.it.k020c1094.endo09.data.Money

class ShopGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_game)

        // アクションバー
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    /**
     * 戻るボタン
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

//    /**
//     * 確認画面から戻ってきた際に所持金を更新する
//     */
//    override fun onStart() {
//        val money = this.application as Money
//        val moneyValue: Int? = money.getMoneyInt()
//
//        val tvMoneyValue = findViewById<TextView>(R.id.tvMoneyValue)
//        tvMoneyValue.text = moneyValue.toString()
//
//        super.onStart()
//    }
}