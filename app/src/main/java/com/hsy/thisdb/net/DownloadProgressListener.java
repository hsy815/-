package com.hsy.thisdb.net;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.net
 * @创始人: hsy
 * @创建时间: 2018/12/13 10:41
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/13 10:41
 * @修改描述:
 */
public interface DownloadProgressListener {
    void onStartDownload(long length);

    void onProgress(int progress);

    void onFail(String errorInfo);
}
