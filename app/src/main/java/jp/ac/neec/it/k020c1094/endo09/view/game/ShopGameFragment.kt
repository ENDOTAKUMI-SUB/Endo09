package jp.ac.neec.it.k020c1094.endo09.view.game


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import jp.ac.neec.it.k020c1094.endo09.R
import jp.ac.neec.it.k020c1094.endo09.data.Money


class ShopGameFragment : Fragment() {
    private var _isLayoutXLarge = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_shop, container, false)
        val lvMenu = view.findViewById<ListView>(R.id.lvShop1)

        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        var menu = mutableMapOf("name" to "アイテム名1", "price" to "800")
        menuList.add(menu)
        menu = mutableMapOf("name" to "アイテム名2", "price" to "850")
        menuList.add(menu)
        menu = mutableMapOf("name" to "アイテム名3", "price" to "860")
        menuList.add(menu)
        menu = mutableMapOf("name" to "アイテム名4", "price" to "870")
        menuList.add(menu)
        menu = mutableMapOf("name" to "アイテム名5", "price" to "880")
        menuList.add(menu)
        menu = mutableMapOf("name" to "アイテム名6", "price" to "890")
        menuList.add(menu)
        menu = mutableMapOf("name" to "アイテム名7", "price" to "900")
        menuList.add(menu)
        menu = mutableMapOf("name" to "アイテム名8", "price" to "910")
        menuList.add(menu)
        menu = mutableMapOf("name" to "アイテム名9", "price" to "920")
        menuList.add(menu)
        menu = mutableMapOf("name" to "アイテム名10", "price" to "930")
        menuList.add(menu)


        val from = arrayOf("name", "price")
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        val adapter =
            SimpleAdapter(activity, menuList, android.R.layout.simple_list_item_2, from, to)
        lvMenu.adapter = adapter

        lvMenu.onItemClickListener = ListItemClickListener()

        val money = activity?.application as Money
        val moneyValue: Int? = money.getMoneyInt()
        val tvMoneyValue = view.findViewById<TextView>(R.id.tvMoneyValueF)
        tvMoneyValue.text = moneyValue.toString()





        return view
    }

    fun changePrice(money:Int) {
        val tvMoneyValue = view?.findViewById<TextView>(R.id.tvMoneyValueF)
        tvMoneyValue?.text = money.toString()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val menuThanksFrame = activity?.findViewById<View>(R.id.menuThanksFrame)
        if (menuThanksFrame == null) {
            _isLayoutXLarge = false
        }
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as MutableMap<String, String>
            val menuName = item["name"]
            val menuPrice = item["price"]


            val money = activity?.application as Money
            val moneyValue: Int? = money.getMoneyInt()

            if (moneyValue!! - menuPrice!!.toInt() >= 0) { // 購入成功
                changePrice(moneyValue!!.toInt() - menuPrice!!.toInt())

                Log.d("==========", "${money.getMoneyInt()}")


            } else { //残高不足

            }


            val bundle = Bundle()
            bundle.putString("menuName", menuName)
            bundle.putString("menuPrice", menuPrice)

            if (_isLayoutXLarge) {
                val transaction = fragmentManager?.beginTransaction()
                val menuThanksFragment = ConfirmationGameFragment()
                menuThanksFragment.arguments = bundle
                transaction?.replace(R.id.menuThanksFrame, menuThanksFragment)
                transaction?.commit()
            } else {
                val intent2MenuThanks = Intent(activity, ConfirmationGameActivity::class.java)
                intent2MenuThanks.putExtras(bundle)
                startActivity(intent2MenuThanks)
            }
        }
    }

}
