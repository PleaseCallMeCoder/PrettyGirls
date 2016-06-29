package coder.prettygirls.data.source;

import coder.prettygirls.data.source.local.LocalGirlsDataSource;
import coder.prettygirls.data.source.remote.RemoteGirlsDataSource;

/**
 * Created by oracleen on 2016/6/29.
 */
public class GirlsResponsitory implements GirlsDataSource {

    private LocalGirlsDataSource mLocalGirlsDataSource;
    private RemoteGirlsDataSource mRemoteGirlsDataSource;

    public GirlsResponsitory() {
        mLocalGirlsDataSource = new LocalGirlsDataSource();
        mRemoteGirlsDataSource = new RemoteGirlsDataSource();
    }

    @Override
    public void getGirls(LoadGirlsCallback callback) {
        mRemoteGirlsDataSource.getGirls(callback);
    }

    @Override
    public void getGirl(GetGirlCallback callback) {
        mRemoteGirlsDataSource.getGirl(callback);
    }

}
