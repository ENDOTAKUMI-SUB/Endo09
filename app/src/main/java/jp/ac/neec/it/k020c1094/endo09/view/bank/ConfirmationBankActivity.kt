package jp.ac.neec.it.k020c1094.endo09.view.bank

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import jp.ac.neec.it.k020c1094.endo09.R
import jp.ac.neec.it.k020c1094.endo09.data.Money

/**
 * ゲームショップの確認画面
 *
 * @author ENDO Takumi
 */
class ConfirmationBankActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_bank)

        // 値を取得
        val withdrawMoney = intent.getStringExtra("withdraw_money")

        // 現在の残高を取得・表示
        val money = this.application as Money
        val moneyValue: Int? = money.getMoneyInt()
        val tvMoneyValue = findViewById<TextView>(R.id.tvMoneyValueF)
        tvMoneyValue.text = moneyValue.toString()

        // メッセージを表示
        val tvMsg = findViewById<TextView>(R.id.tvMsg)
        tvMsg.text = "${withdrawMoney}${getString(R.string.msg_withdraw_money)}"

        findViewById<Button>(R.id.btBack).setOnClickListener(this)
    }

    /**
     * 各種ボタンのクリックイベント
     */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.btBack -> {
                finish()
            }
        }
    }
}