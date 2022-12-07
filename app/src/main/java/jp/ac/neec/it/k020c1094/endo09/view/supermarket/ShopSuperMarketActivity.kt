package jp.ac.neec.it.k020c1094.endo09.view.supermarket


import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import jp.ac.neec.it.k020c1094.endo09.R
import jp.ac.neec.it.k020c1094.endo09.data.Money

/**
 * スーパーマーケット画面
 *
 * @author ENDO Takumi
 */
class ShopSuperMarketActivity : AppCompatActivity() {
    // リストビューに表示するリストデータ。
    private var _menuList: MutableList<MutableMap<String, Any>> = mutableListOf()

    // SimpleAdapterの第4引数fromに使用するプロパティ。
    private val _from = arrayOf("name", "price")

    // SimpleAdapterの第5引数toに使用するプロパティ。
    private val _to = intArrayOf(R.id.tvMenuNameRow, R.id.tvMenuPriceRow)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_super_market)

        // 現在の残高を取得して表示
        val money = this.application as Money
        val tvMoneyValue = findViewById<TextView>(R.id.tvMoneyValueF)
        tvMoneyValue.text = money.getMoneyInt().toString()


        // 商品一覧を定義
        _menuList = createVegetableList()
        val lvMenu = findViewById<ListView>(R.id.lvShop1)
        val adapter =
            SimpleAdapter(this@ShopSuperMarketActivity, _menuList, R.layout.row, _from, _to)
        lvMenu.adapter = adapter
        lvMenu.onItemClickListener = ListItemClickListener()

        registerForContextMenu(lvMenu)

        // アクションバー
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onCreateContextMenu(
        menu: ContextMenu,
        view: View,
        menuInfo: ContextMenu.ContextMenuInfo
    ) {
        super.onCreateContextMenu(menu, view, menuInfo)
        menuInflater.inflate(R.menu.menu_context_menu_list, menu)
        menu.setHeaderTitle(R.string.menu_list_context_header)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var returnVal = true
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        val menu = _menuList[listPosition]

        when (item.itemId) {
            R.id.menuListContextDesc -> {
                val desc = menu["desc"] as String
                Toast.makeText(this@ShopSuperMarketActivity, desc, Toast.LENGTH_LONG).show()
            }
            R.id.menuListContextOrder -> {
                val intent =
                    Intent(
                        this@ShopSuperMarketActivity,
                        ConfirmationSuperMarketActivity::class.java
                    )

                intent.putExtra("menuName", menu["name"].toString())

                // TODO:前回String型で作ってしまったのでそのまま...
                intent.putExtra("menuPrice", menu["price"].toString())

                startActivity(intent)
            }

            else ->
                returnVal = super.onContextItemSelected(item)
        }
        return returnVal
    }


    /**
     *
     */
    private fun createVegetableList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()

        var menus = mutableListOf(
            "トマト ", "れんこん ", "人参 ", "玉葱",
            "長芋", "じゃがいも", "キャベツ", "レタス",
            "ほうれん草", "白菜",
        )

        var prices = mutableListOf(
            800, 850, 850, 1000,
            750, 900, 850, 900,
            850, 750
        )

        var descs = mutableListOf(
            "赤色", "味噌汁に入れるやつ ", "嫌い ", "便利",
            "美味しい", "ポテトサラダ", "まずい", "ふつう",
            "おいしい", "最近食べてる"
        )

        for ((index, value) in menus.withIndex()) {
            var menu: MutableMap<String, Any> =
                mutableMapOf("name" to value, "price" to prices[index], "desc" to descs[index])
            menuList.add(menu)
        }

        return menuList
    }

    /**
     *
     */
    private fun createFruitsList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()

        var menus = mutableListOf(
            "りんご ", "なし ", "いちご ", "スイカ",
            "いちじく", "桃", "みかん", "グレープフツーツ",
            "レモン", "パイナップル",
        )

        var prices = mutableListOf(
            1000, 1850, 1850, 1000,
            1750, 1900, 1850, 1900,
            1850, 1750
        )

        var descs = mutableListOf(
            "赤色", "おいしい ", "たべたい ", "みどり",
            "ふつう", "もも", "オレンジ", "おいしい",
            "食べたい", "ð"
        )

        for ((index, value) in menus.withIndex()) {
            var menu: MutableMap<String, Any> =
                mutableMapOf("name" to value, "price" to prices[index], "desc" to descs[index])
            menuList.add(menu)
        }

        return menuList
    }

    /**
     *
     */
    private fun createOtherList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()

        var menus = mutableListOf(
            "電池", "帽子", "メガネ ", "シャツ",
            "サングラス", "自転車", "水", "ジュース",
            "お酒", "お菓子",
        )

        var prices = mutableListOf(
            1000, 1850, 1850, 1000,
            1750, 1900, 1850, 1900,
            1850, 1750
        )

        var descs = mutableListOf(
            "乾電池", "帽子 ", "メガネ ", "服",
            "おしゃれ", "チャリ", "みず", "飲み物",
            "酒", "おいしい"
        )

        for ((index, value) in menus.withIndex()) {
            var menu: MutableMap<String, Any> =
                mutableMapOf("name" to value, "price" to prices[index], "desc" to descs[index])
            menuList.add(menu)
        }

        return menuList
    }


    /**
     * リストがタップされたときの処理が記述されたメンバクラス。
     */
    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as MutableMap<String, String>
            val intent =
                Intent(this@ShopSuperMarketActivity, ConfirmationSuperMarketActivity::class.java)

            intent.putExtra("menuName", item["name"])

            // TODO:前回String型で作ってしまったのでそのまま...
            intent.putExtra("menuPrice", item["price"].toString())

            startActivity(intent)
        }
    }

    /**
     *
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_options_menu_list, menu)
        return true
    }


    /**
     * 戻るボタン
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var returnVal = true
        when (item.itemId) {
            R.id.menuListOptionVegetable ->
                _menuList = createVegetableList()
            R.id.menuListOptionFruits ->
                _menuList = createFruitsList()
            R.id.menuListOptionOthers ->
                _menuList = createOtherList()
            android.R.id.home ->
                finish()
            else ->
                returnVal = super.onOptionsItemSelected(item)
        }
        val lvMenu = findViewById<ListView>(R.id.lvShop1)
        val adapter =
            SimpleAdapter(this@ShopSuperMarketActivity, _menuList, R.layout.row, _from, _to)
        lvMenu.adapter = adapter
        return returnVal

    }

    /**
     * 確認画面から戻ってきた際に所持金を更新する
     */
    override fun onStart() {
        val money = this.application as Money
        val moneyValue: Int? = money.getMoneyInt()

        val tvMoneyValue = findViewById<TextView>(R.id.tvMoneyValueF)
        tvMoneyValue.text = moneyValue.toString()

        super.onStart()
    }


}