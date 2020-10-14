package com.ppx.dailystudy.chap12materialdesign

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_toolbar.*

/**
 * Author: LuoXia
 * Date: 2020/10/12 19:06
 * Description: ToolBar的简单使用
 */
class ToolBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)

        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
//            setHomeAsUpIndicator(R.drawable.ic_launcher_foreground)
        }

        //设置一个默认选中的item
        navigationview.setCheckedItem(R.id.nav_bags)
        navigationview.setNavigationItemSelectedListener {
            //设置选择了一个item后就将侧滑页关闭
            drawerlayout.closeDrawers()
            true
        }

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
                .setAction("Undo2", object : View.OnClickListener {//事实证明，不能添加两个action。第二个会把第一个给覆盖掉
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