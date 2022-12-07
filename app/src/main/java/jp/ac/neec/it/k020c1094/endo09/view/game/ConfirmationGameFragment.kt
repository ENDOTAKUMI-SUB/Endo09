package jp.ac.neec.it.k020c1094.endo09.view.game

import jp.ac.neec.it.k020c1094.endo09.data.Money

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import jp.ac.neec.it.k020c1094.endo09.R


class ConfirmationGameFragment : Fragment() {

    private var _isLayoutXLarge = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val menuListFragment = fragmentManager?.findFragmentById(R.id.fragmentMenuList)
        if (menuListFragment == null) {
            _isLayoutXLarge = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_confirmation_game, container, false)

        val extras: Bundle?
        if (_isLayoutXLarge) {
            extras = arguments
        } else {
            val intent = activity?.intent
            extras = intent?.extras
        }

        // 定食名と金額を取得。
        val menuName = extras?.getString("menuName")
        val menuPrice = extras?.getString("menuPrice")


        // 現在の残高を取得・表示
        val money = activity?.application as Money
        val moneyValue: Int? = money.getMoneyInt()
        val tvMoneyValue = view.findViewById<TextView>(R.id.tvMoneyValueF)

        if (_isLayoutXLarge) {
        } else {
            tvMoneyValue.text = moneyValue.toString()

        }



        // メッセージを表示
        val tvMsg = view.findViewById<TextView>(R.id.tvMsg)

        if (moneyValue!! - menuPrice!!.toInt() >= 0) { // 購入成功
            money.setMoneyInt(moneyValue!!.toInt() - menuPrice!!.toInt())
            tvMsg.text =
                "${getString(R.string.msg_success)} \n $menuName \n $menuPrice 円"



        } else { //残高不足
            tvMsg.text = getString(R.string.msg_ng)

        }


        val btBackButton = view.findViewById<Button>(R.id.btBack)
        btBackButton.setOnClickListener(ButtonClickListener())

        return view
    }

    /**
     * ボタンが押されたときの処理が記述されたメンバクラス。
     */
    private inner class ButtonClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            if (_isLayoutXLarge) {
                val transaction = fragmentManager?.beginTransaction()
                transaction?.remove(this@ConfirmationGameFragment)
                transaction?.commit()
            } else {
                activity?.finish()
            }
        }
    }
}
