package front.frontstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    var navHostFragment: NavHostFragment? = null
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentPlaceHolder) as NavHostFragment
        navController = navHostFragment?.navController
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().also {
                navController?.navigate(R.id.homeFragment)
            }
        }
    }
}