package co.windly.aac.utilities

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import co.windly.aac.R

class CommonUtils private constructor() {

  companion object {

    fun showLoadingDialog(context: Context): ProgressDialog {
      // TODO: Handle deprecation.
      return ProgressDialog(context).apply {
        this.show()
        if (this.window != null) {
          this.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        this.setContentView(R.layout.progress_dialog)
        this.isIndeterminate = true
        this.setCancelable(false)
        this.setCanceledOnTouchOutside(false)
      }
    }
  }
}