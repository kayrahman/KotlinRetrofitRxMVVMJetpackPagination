package com.nkr.vumobile


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nkr.vumobile.ui.ImageGalleryFragment
import com.nkr.vumobile.util.FragmentHelper


private const val IMAGE_GALLERY_FRAGMENT = "image_gallery_fragment"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val view = supportFragmentManager.findFragmentByTag(IMAGE_GALLERY_FRAGMENT) as ImageGalleryFragment?
            ?: ImageGalleryFragment()



        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_nav, view, IMAGE_GALLERY_FRAGMENT)
            .commitNowAllowingStateLoss()


        view.listener = object : ImageGalleryFragment.Listener{
            override fun goToFragment(fragment: Fragment) {
                FragmentHelper.loadFragment(supportFragmentManager,fragment,"image_gallery",R.id.fragment_nav)

            }

        }


    }

}
