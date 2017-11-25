package coder.prettygirls.data.source;

import coder.prettygirls.data.source.local.LocalGirlsDataSource;
import coder.prettygirls.data.source.remote.RemoteGirlsDataSource;

/**
 * Created by oracleen on 2016/6/29.
 */
public class GirlsRepository implements GirlsDataSource {

    private LocalGirlsDataSource mLocalGirlsDataSource;
    private RemoteGirlsDataSource mRemoteGirlsDataSource;

    public GirlsRepository() {
        mLocalGirlsDataSource = new LocalGirlsDataSource();
        mRemoteGirlsDataSource = new RemoteGirlsDataSource();
    }

    @Override
    public void getGirls(int page, int size, LoadGirlsCallback callback) {
        mRemoteGirlsDataSource.getGirls(page, size, callback);
    }

    @Override
    public void getGirl(LoadGirlsCallback callback) {
        mRemoteGirlsDataSource.getGirl(callback);
    }

}
