package yzq.com.iosdialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import yzq.com.library.CommonViewDialog
import yzq.com.library.CommonViewDialog.OnClickBottomListener

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val  button1=findViewById<Button>(R.id.button1)
        val  button2=findViewById<Button>(R.id.button2)
        val  button3=findViewById<Button>(R.id.button3)
        val  button4=findViewById<Button>(R.id.button4)
        val  button5=findViewById<Button>(R.id.button5)
        button1.setOnClickListener {
            common()
        }
        button2.setOnClickListener {
            noTitle()
        }
        button3.setOnClickListener {
            noBottom()
        }
        button4.setOnClickListener {
            single()
        }
        button5.setOnClickListener {
            view()
        }

    }

    fun  common(){
        var  dialog= CommonViewDialog(this)
        dialog.setTitle("提示")
        dialog.setMessage("这是一个dialog！")
        dialog.setNegtive("取消")
        dialog.setPositive("确定")
        dialog.show()
        val  listener=object :CommonViewDialog.OnClickBottomListener{
            override fun onPositiveClick() {
                dialog.dismiss()
            }

            override fun onNegtiveClick() {
                dialog.dismiss()
            }
        }
        dialog.setOnClickBottomListener(listener)
    }
    fun  noTitle(){
        var  dialog= CommonViewDialog(this)
        dialog.setMessage("这是一个dialog！")
        dialog.setNegtive("取消")
        dialog.setPositive("确定")
        dialog.show()
    }
    fun  noBottom(){
        var  dialog:CommonViewDialog= CommonViewDialog(this)
        dialog.setTitle("提示")
        dialog.setMessage("这是一个dialog！")
        dialog.setSingle(CommonViewDialog.Bottom.GONE)
        dialog.show()
    }
    fun  single(){
        var  dialog= CommonViewDialog(this)
        dialog.setTitle("提示")
        dialog.setMessage("这是一个dialog！")
        dialog.setPositive("确定")
        dialog.setSingle(CommonViewDialog.Bottom.SINGLE)
        dialog.show()

    }
    fun  view(){
        var  dialog= CommonViewDialog(this)
        dialog.setTitle("提示")
        dialog.setPositive("确定")
        dialog.setSingle(CommonViewDialog.Bottom.SINGLE)
        val view=LayoutInflater.from(this).inflate(R.layout.layout,null);
        dialog.setView(view)
        dialog.show()


    }
}
