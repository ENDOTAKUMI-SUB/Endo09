package jp.ac.neec.it.k020c1094.endo09


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import jp.ac.neec.it.k020c1094.endo09.view.bank.BankActivity
import jp.ac.neec.it.k020c1094.endo09.data.Money
import jp.ac.neec.it.k020c1094.endo09.view.game.ShopGameActivity
import jp.ac.neec.it.k020c1094.endo09.view.supermarket.ShopSuperMarketActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edMoney = findViewById<EditText>(R.id.edMoney)
        val money = this.application as Money
        val moneyValue: Int? = money.getMoneyInt()

        edMoney.setText(moneyValue.toString())

        findViewById<Button>(R.id.btShop1).setOnClickListener(this)
        findViewById<Button>(R.id.btShop2).setOnClickListener(this)
        findViewById<Button>(R.id.btBanks).setOnClickListener(this)
    }

    /**
     * 各種ボタンのクリックイベント
     */
    override fun onClick(view: View) {
        val money = this.application as Money
        val edMoney = findViewById<EditText>(R.id.edMoney)
        val edUserName = findViewById<EditText>(R.id.edUserName)


        if (
            edUserName.text.toString().isEmpty() || edMoney.text.toString().isEmpty()
        ) { // エディットテキストがからの場合
            Toast.makeText(
                applicationContext,
                getString(R.string.toast_ng3),
                Toast.LENGTH_LONG
            ).show()
        } else { // 正常
            money.setMoneyInt(Integer.parseInt(edMoney.text.toString()))

            when (view.id) {
                R.id.btShop1 -> { // ゲームショップ画面に遷移
                    val intent = Intent(this@MainActivity, ShopGameActivity::class.java)
                    startActivity(intent)
                }
                R.id.btShop2 -> { // スーパーマーケット画面に繊維
                    val intent = Intent(this@MainActivity, ShopSuperMarketActivity::class.java)
                    startActivity(intent)
                }
                R.id.btBanks -> { // 銀行画面に繊維
                    val intent = Intent(this@MainActivity, BankActivity::class.java)
                    intent.putExtra("userName", edUserName.text.toString())
                    startActivity(intent)
                }
            }
        }
    }

    /**
     * 確認画面から戻ってきた際に所持金を更新する
     */
    override fun onStart() {
        val edMoney = findViewById<EditText>(R.id.edMoney)
        val money = this.application as Money
        val moneyValue: Int? = money.getMoneyInt()

        edMoney.setText(moneyValue.toString())

        super.onStart()
    }
}