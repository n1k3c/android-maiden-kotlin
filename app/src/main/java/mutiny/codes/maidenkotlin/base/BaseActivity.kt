package mutiny.codes.maidenkotlin.base

import android.arch.lifecycle.LifecycleRegistry
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.NavUtils
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Surface
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import dmax.dialog.SpotsDialog
import es.dmoral.toasty.Toasty
import mutiny.codes.maidenkotlin.R
import mutiny.codes.maidenkotlin.TheApplication
import mutiny.codes.maidenkotlin.di.components.AppComponent

/**
 * Created by nikola on 5/24/17.
 */

const val NO_ITEMS = 0

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private lateinit var lifecycleRegistry: LifecycleRegistry

    private lateinit var progressDialog: SpotsDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewResource)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(true)
        }

        progressDialog = SpotsDialog(this)

        lifecycleRegistry = LifecycleRegistry(this)

        injectDependencies(appComponent = TheApplication.appComponent)

    }

    protected abstract val contentViewResource: Int

    protected abstract fun injectDependencies(appComponent: AppComponent)

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    override fun showProgress() {
        progressDialog.show()
    }

    override fun hideProgress() {
        progressDialog.dismiss()
    }

    private fun hideKeyboard() {
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
        for (i in 0 until fm.backStackEntryCount) {
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
            return supportFragmentManager
                    .findFragmentByTag(fragmentTag)
        }

    fun removeFrontFragment(): Boolean {
        return supportFragmentManager.popBackStackImmediate()
    }

    protected val isPortrait: Boolean
        get() {
            val orientation = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.rotation
            return orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180
        }

    public fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).setDisplayShowTitleEnabled(true)
            supportActionBar?.title = title
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

    override fun showSuccessToast(context: Context, message: String) {
        Toasty.success(context, message, Toast.LENGTH_SHORT, true).show();
    }

    override fun showErrorToast(context: Context, message: String) {
        Toasty.error(context, message, Toast.LENGTH_SHORT, true).show();
    }

    override fun showInfoToast(context: Context, message: String) {
        Toasty.info(context, message, Toast.LENGTH_SHORT, true).show();
    }
}
