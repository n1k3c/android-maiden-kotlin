package mutiny.codes.maidenkotlin.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pawegio.kandroid.d
import mutiny.codes.maidenkotlin.R
import mutiny.codes.maidenkotlin.TheApplication
import mutiny.codes.maidenkotlin.activities.BaseActivity
import mutiny.codes.maidenkotlin.dagger.components.AppComponent
import mutiny.codes.maidenkotlin.interfaces.ActionBarResourceProvider
import mutiny.codes.maidenkotlin.mvp.BaseView

/**
 * Created by nikola on 5/24/17.
 */

abstract class BaseFragment : Fragment(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies(appComponent = TheApplication.appComponent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(contentViewResource, container, false)

        return view
    }

    protected abstract fun injectDependencies(appComponent: AppComponent)

    protected abstract val contentViewResource: Int

    protected fun setActionBarTitle(title: String) {
        if (activity != null && activity is ActionBarResourceProvider) {
            (activity as ActionBarResourceProvider).setTitle(title)
        }
    }

    protected fun setActionBarIcon(icon: Int) {
        if (activity != null && activity is ActionBarResourceProvider) {
            (activity as ActionBarResourceProvider).setIcon(icon)
        }
    }

    override fun showProgress() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showProgress()
        } else {
            d(getString(R.string.extends_base_activity))
        }
    }

    override fun showProgress(@StringRes description: Int) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showProgress(description)
        } else {
            d(getString(R.string.extends_base_activity))
        }
    }

    override fun hideProgress() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).hideProgress()
        } else {
            d(getString(R.string.extends_base_activity))
        }
    }

    override fun showMessage(description: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showMessage(description)
        } else {
            d(getString(R.string.extends_base_activity))
        }
    }

    override fun showMessage(message: String, description: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showMessage(message, description)
        } else {
            d(getString(R.string.extends_base_activity))
        }
    }

    override fun showMessage(message: String, description: String, cancelable: Boolean) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showMessage(message, description, cancelable)
        } else {
            d(getString(R.string.extends_base_activity))
        }
    }

    override fun showMessage(message: String, description: String, listener: DialogInterface.OnClickListener, cancelable: Boolean) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showMessage(message, description, listener, cancelable)
        } else {
            d(getString(R.string.extends_base_activity))
        }
    }

    override fun showError(description: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showError(description)
        } else {
            d(getString(R.string.extends_base_activity))
        }
    }
}