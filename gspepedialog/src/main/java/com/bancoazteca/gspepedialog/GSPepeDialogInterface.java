package com.bancoazteca.gspepedialog;

public interface GSPepeDialogInterface {

    void dismiss();

    public interface OnClickListener {
        void OnClick(GSPepeDialogInterface dialog);
    }
}
