package mutiny.codes.maidenkotlin.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.NavUtils
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Surface
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.pawegio.kandroid.find

import mutiny.codes.maidenkotlin.R
import mutiny.codes.maidenkotlin.TheApplication
import mutiny.codes.maidenkotlin.dagger.components.AppComponent
import mutiny.codes.maidenkotlin.fragments.BaseFragment
import mutiny.codes.maidenkotlin.mvp.BaseView

/**
 * Created by nikola on 5/24/17.
 */

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewResource)

        val toolbar = find<Toolbar>(R.id.toolbar)

        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(true)
        }

        injectDependencies(appComponent = TheApplication.appComponent)
    }

    protected abstract val contentViewResource: Int

    protected abstract fun injectDependencies(appComponent: AppComponent)

    override fun showProgress() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, getString(R.string.app_name), null, true, false)
        } else if (!progressDialog!!.isShowing) {
            (progressDialog as ProgressDialog).show()
        }
    }

    override fun showProgress(@StringRes description: Int) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, getString(R.string.app_name), getString(description), true, false)
        } else if (!(progressDialog as ProgressDialog).isShowing) {
            (progressDialog as ProgressDialog).show()
        }
    }

    override fun hideProgress() {
        if (progressDialog != null) {
            (progressDialog as ProgressDialog).dismiss()
        }
    }

    protected fun hideKeyboard() {
        val focusedView = currentFocus
        if (focusedView != null) {
            focusedView.clearFocus()
            val imm = focusedView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(focusedView.windowToken, 0)
        }
    }

    override fun showMessage(description: String) {
        showMessage(getString(R.string.app_name), description, DialogInterface.OnClickListener { _, _ -> }, false)
    }

    override fun showMessage(message: String, description: String) {
        showMessage(message, description, false)
    }

    override fun showMessage(message: String, description: String, cancelable: Boolean) {
        showMessage(message, description, DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() }, cancelable)
    }

    override fun showMessage(message: String, description: String, listener: DialogInterface.OnClickListener, cancelable: Boolean) {
        if (!isFinishing) {
            hideKeyboard()
            val cancelListener = DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() }
            val builder = AlertDialog.Builder(this)
            builder.setCancelable(cancelable)
            builder.setTitle(message)
                    .setMessage(description)
                    .setPositiveButton(android.R.string.ok, listener)
            if (cancelable) {
                builder.setNegativeButton(android.R.string.cancel, cancelListener)
            }
            builder.show()
        }
    }

    override fun showError(description: String) {
        showMessage(getString(R.string.error_title), description, false)
    }

    fun replaceFragment(@IdRes fragmentContainerId: Int, fragment: BaseFragment, tag: String, shouldAddToBackStack: Boolean = false) {
        val fTrans = supportFragmentManager.beginTransaction()
        fTrans.replace(fragmentContainerId, fragment, tag)
        if (shouldAddToBackStack) {
            fTrans.addToBackStack(tag)
        }
        fTrans.commit()
    }

    fun addFragment(@IdRes fragmentContainerId: Int, tag: String, fragment: Fragment, shouldAddToBackStack: Boolean = false) {
        val fTrans = supportFragmentManager.beginTransaction()
        fTrans.add(fragmentContainerId, fragment, tag)
        if (shouldAddToBackStack) {
            fTrans.addToBackStack(tag)
        }
        fTrans.commit()
    }

    fun clearFragments() {
        val fm = supportFragmentManager
        for (i in 0..fm.backStackEntryCount - 1) {
            fm.popBackStack()
        }
    }

    val frontFragment: Fragment?
        get() {
            val fragmentManager = supportFragmentManager
            if (fragmentManager.backStackEntryCount == NO_ITEMS) {
                return null
            }
            val entry = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1) ?: return null
            val fragmentTag = entry.name
            val currentFragment = supportFragmentManager
                    .findFragmentByTag(fragmentTag)
            return currentFragment
        }

    fun removeFrontFragment(): Boolean {
        return supportFragmentManager.popBackStackImmediate()
    }

    protected val isPortrait: Boolean
        get() {
            val orientation = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.rotation
            return orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180
        }

    private fun setActionBarTitle(@StringRes stringRes: Int) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).setDisplayShowTitleEnabled(true)
            supportActionBar?.setTitle(stringRes)
        }
    }

    protected fun setupActionBarBack() {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).setHomeButtonEnabled(true)
            (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onBackPressed() {
        val parentActivityIntent = NavUtils.getParentActivityIntent(this)
        // if a parent activity is defined via manifest this will return an intent
        if (parentActivityIntent != null) {
            parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(parentActivityIntent)
            finish()
        } else {
            super.onBackPressed()
        }
    }

    companion object {

        val NO_ITEMS = 0
    }
}
