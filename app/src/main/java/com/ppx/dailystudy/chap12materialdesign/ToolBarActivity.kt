package com.ppx.dailystudy.chap12materialdesign

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_toolbar.*
import java.lang.Exception
import kotlin.random.Random

/**
 * Author: LuoXia
 * Date: 2020/10/12 19:06
 * Description: 体验material design风格的 控件 。如：DrawerLayout、NavigationView、ToolBar、AppBarLayout、FloatingActionButton、SnackBar、CoordinateLayout的简单使用
 */
class ToolBarActivity : AppCompatActivity() {
    private val TAG = "ToolBarActivity"
    private var cardViewAdapter: CardViewAdapter = CardViewAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)

        setSupportActionBar(toolbar)

        //actionBar
        val actionBar = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
//            setHomeAsUpIndicator(R.drawable.ic_launcher_foreground)
        }

        navigationView()

        floatingActionBar()
        initRVCardView()

        useSwipeRefresh()
    }

    /**
     * SwipeRefreshLayout
     */
    private fun useSwipeRefresh() {
        swipe_refresh.setColorSchemeColors(resources.getColor(R.color.colorPrimary))
        swipe_refresh.setOnRefreshListener {
            Thread {
                try {
                    Thread.sleep(2000)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                runOnUiThread {
                    initRVCardView()
                    cardViewAdapter.notifyDataSetChanged()
                    swipe_refresh.isRefreshing = false
                }
            }.start()
        }
    }

    /**
     * CardView
     */
    private fun initRVCardView() {
        val fruitsList = mutableListOf<FruitBean>()
        val imgArray = intArrayOf(
            R.mipmap.apple,
            R.mipmap.cherry,
            R.mipmap.mango,
            R.mipmap.orange,
            R.mipmap.pear,
            R.mipmap.pineapple,
            R.mipmap.strawberry
        )
        for (i in 0..50) {
            val random = 0 + Math.random() * 6

            val bean1 = FruitBean(imgArray[random.toInt()], "apple")
            fruitsList.add(bean1)
        }
        cardViewAdapter = CardViewAdapter(fruitsList)

        rv_cardview.layoutManager = GridLayoutManager(this, 3)
        rv_cardview.adapter = cardViewAdapter

        cardViewAdapter.setOnItemClickListener { adapter, view, position ->
            val bean = adapter.getItem(position) as FruitBean
            val intent = Intent(this, CollaspcingToobarActivity::class.java)
            intent.putExtra(CollaspcingToobarActivity.FRUIT_NAME, bean.name)
            intent.putExtra(CollaspcingToobarActivity.FRUIT_IMAGE_ID, bean.img)
            startActivity(intent)
        }
    }

    /**
     * navigationView
     */
    private fun navigationView() {
        //设置一个默认选中的item
        navigationview.setCheckedItem(R.id.nav_bags)
        navigationview.setNavigationItemSelectedListener {
            //设置选择了一个item后就将侧滑页关闭
            drawerlayout.closeDrawers()
            true
        }
    }

    /**
     * floatingActionButton &  SnackBar
     */
    private fun floatingActionBar() {
        fab_button.setOnClickListener {
            /**
             * setAction:参数一：底部弹出的那个匡匡的右侧的可点击的文字，
             * 参数二：点击事件
             *  Snackbar.make(it, "floatingactionbutton",....    第二个参数，snackbar弹出的时候左边的文字显示
             */
            Snackbar.make(it, "floatingactionbutton", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    Toast.makeText(
                        this@ToolBarActivity,
                        "click floatingactionbutton",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setAction("Undo2", object : View.OnClickListener {
                    //事实证明，不能添加两个action。第二个会把第一个给覆盖掉
                    override fun onClick(v: View?) {
                        Toast.makeText(
                            this@ToolBarActivity,
                            "click floatingactionbutton",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
                .show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show()
            }
            R.id.add -> {
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show()
            }
            R.id.delete -> {
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
            }
            android.R.id.home -> {
                drawerlayout.openDrawer(GravityCompat.START)
            }
        }
        return true
    }

}