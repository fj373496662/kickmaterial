package com.byoutline.kickmaterial.projectlist

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import com.byoutline.kickmaterial.R
import com.byoutline.kickmaterial.model.Category
import com.byoutline.kickmaterial.selectcategory.ARG_CATEGORY
import com.byoutline.kickmaterial.selectcategory.CategoriesListActivity
import com.byoutline.kickmaterial.selectcategory.DataManager
import com.byoutline.kickmaterial.utils.KickMaterialBaseActivity


/**
 * @author Pawel Karczewski <pawel.karczewski at byoutline.com> on 2015-01-03
 */
class MainActivity : KickMaterialBaseActivity() {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectViewsAndSetUpToolbar()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, ProjectsListFragment.newInstance(DataManager.categoryAll))
                    .commit()
        }
    }

    override fun setToolbarAlpha(alpha: Float) {
        toolbar.background.alpha = (alpha * 255).toInt()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val categorySelection = requestCode == CategoriesListActivity.DEFAULT_REQUEST_CODE
        if (categorySelection && resultCode == CategoriesListActivity.RESULT_CATEGORY_SELECTED) {
            val category = data?.getParcelableExtra<Category>(ARG_CATEGORY) ?: return
            showFragment(ProjectsListFragment.newInstance(category), true)
            setToolbarText(getString(category.nameResId))
        }
    }
}