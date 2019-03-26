package yzq.com.iosdialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import yzq.com.library.CommonViewDialog
import yzq.com.library.CommonViewDialog.OnClickBottomListener

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        common()
    }

    fun  common(){
        var  dialog:CommonViewDialog= CommonViewDialog(this)
        dialog.setTitle("提示")
        dialog.setMessage("这是一个dialog！")
        dialog.setNegtive("取消")
        dialog.setPositive("确定")
       dialog.setOnClickBottomListener(object :OnClickBottomListener{
           override fun onPositiveClick() {
               TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
           }

           override fun onNegtiveClick() {
               TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
           }

       })
        dialog.show()
    }
}
