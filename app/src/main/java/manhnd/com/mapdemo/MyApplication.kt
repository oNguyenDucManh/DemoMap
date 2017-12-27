package manhnd.com.mapdemo

import android.app.Application
import android.content.Context


/**
 * Created by manhi on 4/1/2017.
 */

class MyApplication : Application() {

    fun getApiService(): ApiService = ApiService.Factory.create(this)

    companion object {
        fun with(context: Context): MyApplication = context.applicationContext as MyApplication
    }

    override fun onCreate() {
        super.onCreate()
//        changeAppFont()
    }

}