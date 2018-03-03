package mutiny.codes.maidenkotlin.base

import android.content.Context
import android.content.DialogInterface
import android.support.annotation.StringRes


/**
 * Created by nikola on 5/24/17.
 */

interface BaseView {

    fun showProgress()

    fun hideProgress()

    fun showMessage(description: String)

    fun showMessage(message: String, description: String)

    fun showMessage(message: String, description: String, cancelable: Boolean)

    fun showMessage(message: String, description: String, listener: DialogInterface.OnClickListener, cancelable: Boolean)

    fun showError(description: String)

    fun showSuccessToast(context: Context, message: String)

    fun showErrorToast(context: Context, message: String)

    fun showInfoToast(context: Context, message: String)
}