package jp.ac.neec.it.k020c1094.endo09.view.supermarket


import android.os.Bundle
import android.view.MenuItem

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import jp.ac.neec.it.k020c1094.endo09.R
import jp.ac.neec.it.k020c1094.endo09.data.Money

/**
 * 銀行の確認画面
 *
 * @author ENDO Takumi
 */
class ConfirmationSuperMarketActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_super_market)

        // 値を取得
        val menuName = intent.getStringExtra("menuName")
        val menuPrice = intent.getStringExtra("menuPrice")

        // 現在の残高を取得・表示
        val money = this.application as Money
        val moneyValue: Int? = money.getMoneyInt()
        val tvMoneyValue = findViewById<TextView>(R.id.tvMoneyValueF)
        tvMoneyValue.text = moneyValue.toString()

        // メッセージを表示
        val tvMsg = findViewById<TextView>(R.id.tvMsg)

        if (moneyValue!!.toInt() - menuPrice!!.toInt() >= 0) { // 購入成功
            money.setMoneyInt(moneyValue!!.toInt() - menuPrice!!.toInt())
            tvMsg.text =
                "${getString(R.string.msg_success)} \n $menuName \n ${menuPrice.toString()} 円"

        } else { //残高不足
            tvMsg.text = getString(R.string.msg_ng)
        }

        // アクションバー
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * 戻るボタン
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnVal = true
        when (item.itemId) {
            android.R.id.home ->
                finish()
            else ->
                returnVal = super.onOptionsItemSelected(item)
        }
        return returnVal
    }
}