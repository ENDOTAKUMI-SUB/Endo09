package jp.ac.neec.it.k020c1094.endo09.view.bank

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import jp.ac.neec.it.k020c1094.endo09.R
import jp.ac.neec.it.k020c1094.endo09.data.Money

/**
 * 銀行画面
 *
 * @author ENDO Takumi
 */
class BankActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank)

        // 値を取得
        val userName = intent.getStringExtra("userName")

        // 現在の残高を取得・表示
        val money = this.application as Money
        val moneyValue: Int? = money.getMoneyInt()

        // ユーザー名・所持金を表示
        val tvUserNameValue = findViewById<TextView>(R.id.tvUserNameValue)
        val tvPossessionMoney = findViewById<TextView>(R.id.tvPossessionMoney)
        tvUserNameValue.text = userName
        tvPossessionMoney.text = moneyValue.toString()

        findViewById<Button>(R.id.btWithdrawMoney).setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val Money = this.application as Money
        val moneyValue: Int? = Money.getMoneyInt()
        val edWithdrawMoney = findViewById<EditText>(R.id.edWithdrawMoney)


        when (view.id) {
            R.id.btWithdrawMoney -> {
                when {
                    edWithdrawMoney.text.toString().isEmpty() -> { // エディットテキストがからの場合
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.toast_ng1),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    edWithdrawMoney.text.toString().toInt() <= 0 -> { // エディットテキストの中身が 0 以下の場合
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.toast_ng1),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    moneyValue!! < edWithdrawMoney.text.toString().toInt() -> {  // 引き出す金額が所持金より多い場合
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.toast_ng2),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else -> { // 正常な値が格納されている場合
                        Money.setMoneyInt(moneyValue!! - edWithdrawMoney.text.toString().toInt())


                        val intent =
                            Intent(this@BankActivity, ConfirmationBankActivity::class.java)
                        intent.putExtra(
                            "withdraw_money",
                            edWithdrawMoney.text.toString()
                        )

                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}