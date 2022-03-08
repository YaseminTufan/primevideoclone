package com.yasemintufan.primevideoclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.yasemintufan.primevideoclone.adapter.MainRecyclerAdapter;
import com.yasemintufan.primevideoclone.adapter.VPAdapter;
import com.yasemintufan.primevideoclone.model.AllCategory;
import com.yasemintufan.primevideoclone.model.BannerMovies;
import com.yasemintufan.primevideoclone.model.CategoryItem;
import com.yasemintufan.primevideoclone.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    TabLayout indicatorTab, categoryTab;
    ViewPager2 viewPager2;
    VPAdapter vpAdapter;
    List<BannerMovies> homeBannerList;
    List<BannerMovies> tvShowBannerList;
    List<BannerMovies> movieBannerList;
    List<BannerMovies> kidsBannerList;
    Timer sliderTimer;
    NestedScrollView nestedScrollView;
    AppBarLayout appBarLayout;


    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;
    List<AllCategory> allCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nestedScrollView = findViewById(R.id.nested_scroll);
        appBarLayout = findViewById(R.id.appbar);


        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerMovies(1, "AQUA MAN", "https://m.media-amazon.com/images/M/MV5BOTk5ODg0OTU5M15BMl5BanBnXkFtZTgwMDQ3MDY3NjM@._V1_UX182_CR0,0,182,268_AL_.jpg", ""));
        homeBannerList.add(new BannerMovies(2, "IRON MAN", "https://image.tmdb.org/t/p/w300/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg", ""));
        homeBannerList.add(new BannerMovies(3, "ALL GOOD THINGS", "https://m.media-amazon.com/images/M/MV5BMTcwMjIyMTc2Nl5BMl5BanBnXkFtZTcwOTQyMzc5Mw@@._V1_UY209_CR0,0,140,209_AL_.jpg", ""));
        homeBannerList.add(new BannerMovies(4, "CAR", "https://i.picsum.photos/id/892/200/300.jpg?hmac=9MUtm-RM2UIFVmP8I80S9TuWKk93ZEPqQLpOf-y1BwE", ""));
        homeBannerList.add(new BannerMovies(5, "NEW YORK", "https://i.picsum.photos/id/441/200/300.webp?hmac=s6iKwzGyh9v38Csgx7E6qeo6ZwlXSB2UFRxVxnr8oLo", ""));

        tvShowBannerList = new ArrayList<>();
        tvShowBannerList.add(new BannerMovies(1, "MOUNTAIN", "https://i.picsum.photos/id/591/200/300.jpg?grayscale&hmac=lWKdCeiRp58dAgnvp0U9dDB82HsHYeP6LAS-egi5Xlk", ""));
        tvShowBannerList.add(new BannerMovies(2, "VERTİCAL LİMİT", "https://i.picsum.photos/id/1076/200/300.jpg?blur=2&hmac=0zpdXPuNbARqZN44mDS8nFnC1uEVlAvIgZnbyefqW8w", ""));
        tvShowBannerList.add(new BannerMovies(3, "THE WAY", "https://i.picsum.photos/id/544/200/300.jpg?hmac=YL3M_fg_84Kqg0EQTvbltmjeGeQetARWPFA5YLn5hS0", ""));
        tvShowBannerList.add(new BannerMovies(4, "LIGHT HOUSE", "https://i.picsum.photos/id/870/200/300.jpg?blur=2&grayscale&hmac=ujRymp644uYVjdKJM7kyLDSsrqNSMVRPnGU99cKl6Vs", ""));

        movieBannerList = new ArrayList<>();
        movieBannerList.add(new BannerMovies(1, "DOG", "https://i.picsum.photos/id/237/200/300.jpg?hmac=TmmQSbShHz9CdQm0NkEjx1Dyh_Y984R9LpNrpvH2D_U", ""));
        movieBannerList.add(new BannerMovies(2, "CLOUDS", "https://i.picsum.photos/id/866/200/300.jpg?hmac=rcadCENKh4rD6MAp6V_ma-AyWv641M4iiOpe1RyFHeI", ""));
        movieBannerList.add(new BannerMovies(3, "SEA", "https://i.picsum.photos/id/716/200/300.jpg?grayscale&hmac=3uAqCxkEelBms9FiOzzpKVF8hscYRkSuxVrSYOCHT1s", ""));

        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new BannerMovies(1, "MINYON", "https://i.picsum.photos/id/627/200/300.jpg?hmac=C6cEU431ILuZjftVFQ1RsBlFYS52ym9f2KZPSOfH-R4", ""));
        kidsBannerList.add(new BannerMovies(2, "ICE AGE", "https://i.picsum.photos/id/1024/200/300.webp?hmac=OosFDUXzB_ElCGHfrZawsnznS7guMZTF5Dlq7iwAMrA", ""));
        kidsBannerList.add(new BannerMovies(3, "PETER PAN", "https://i.picsum.photos/id/977/200/300.jpg?blur=2&hmac=FoDPsjColLDk9-z5oVMa44_w-qRzalKkMfxKxk6hvfc", ""));
        //this is default tab selected
        setVpAdapter(homeBannerList);
        //on tab change selected data
        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        setScrollDefaultState();

                        setVpAdapter(tvShowBannerList);

                        return;
                    case 2:
                        setScrollDefaultState();

                        setVpAdapter(movieBannerList);
                        return;

                    case 3:
                        setScrollDefaultState();

                        setVpAdapter(kidsBannerList);
                        return;
                    default:
                        setScrollDefaultState();

                        setVpAdapter(homeBannerList);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        List<CategoryItem> homeCatListItem1 = new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1, "Street", "https://i.picsum.photos/id/70/200/300.webp?hmac=GdbShv7fK-cRAWhwm--BzoT61Jbp1A6cdEv1Z1dvc_o", ""));
        homeCatListItem1.add(new CategoryItem(2, "Building", "https://i.picsum.photos/id/1048/200/300.jpg?hmac=0UWwJ_psvsU4JEovhQAStv9fWlVd7reEtF624_vHbbk", ""));
        homeCatListItem1.add(new CategoryItem(3, "Fog", "https://i.picsum.photos/id/1045/200/300.jpg?blur=2&hmac=ER3hqFbEfiJqrwpWViTkngda-G-abyuS9nKifNpfvpw", ""));
        homeCatListItem1.add(new CategoryItem(4, "Smoke", "https://i.picsum.photos/id/186/200/300.jpg?blur=5&hmac=OAX3kZelcXudbmgRTHuDrRMWXvwk1Bx6foOhmfCO2Rc", ""));
        homeCatListItem1.add(new CategoryItem(5, "Flu", "https://i.picsum.photos/id/125/200/300.jpg?grayscale&hmac=09O6wgdScdf2KQHGY3PPlnK9Etw_JHayhVHkzlS7AWs", ""));

        List<CategoryItem> homeCatListItem2 = new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1, "Vaka Vaka", "https://i.picsum.photos/id/481/200/300.jpg?blur=5&hmac=jmDbrdVqwNwTO8ITWxnfyT_EcvONklkmNN4hGU8WOck", ""));
        homeCatListItem1.add(new CategoryItem(2, "Rock", "https://i.picsum.photos/id/651/200/300.jpg?grayscale&hmac=9Ps2ai_oohMHcWk6ZnXHIsxTADbZzGcoFKbORFcDYo8", ""));
        homeCatListItem1.add(new CategoryItem(3, "Book", "https://i.picsum.photos/id/534/200/300.jpg?hmac=-mwH1XukRF8901AgoSI9MZSPdET9wCx3l43x0IkJSsU", ""));
        homeCatListItem1.add(new CategoryItem(4, "Sun", "https://i.picsum.photos/id/388/200/300.webp?hmac=QOQb5q3tda7Sk0Sxu92RDHVjX7ISKm57_JpoMD3-PDg", ""));
        homeCatListItem1.add(new CategoryItem(5, "Leaf", "https://i.picsum.photos/id/309/200/300.jpg?hmac=gmsts4-400Ihde9dfkfZtd2pQnbZorV4nBKlLOhbuMs", ""));

        List<CategoryItem> homeCatListItem3 = new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1, "Girl", "https://i.picsum.photos/id/65/200/300.jpg?hmac=o9HaDBPcrCPi8zfB6MoTe6MNNVPsEN4orpzsHhCPlbU", ""));
        homeCatListItem1.add(new CategoryItem(2, "Green", "https://i.picsum.photos/id/688/200/300.jpg?hmac=6_iDeSdl4f6R2Lre1xFrJ9VaO8OQHMJD_PL5lEypBGI", ""));
        homeCatListItem1.add(new CategoryItem(3, "Bicycle", "https://i.picsum.photos/id/146/200/300.webp?hmac=UdydNqsQkkLyUXq1GtKRZqDqe1fIEXKO6Y1347l5Lr4", ""));
        homeCatListItem1.add(new CategoryItem(4, "Eiffel", "https://i.picsum.photos/id/318/200/300.jpg?blur=5&hmac=W5FtY2izWUbNavDAgSbjDwMFbFS_8JvbBEzK5sEylc8", ""));
        homeCatListItem1.add(new CategoryItem(5, "Dock", "https://i.picsum.photos/id/637/200/300.jpg?grayscale&hmac=0cI9GUo1G57ARGJ9n4JPhFT2AG21fZQAUjESuQGW3jI", ""));

        List<CategoryItem> homeCatListItem4 = new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1, "Snow", "https://i.picsum.photos/id/866/200/300.jpg?hmac=rcadCENKh4rD6MAp6V_ma-AyWv641M4iiOpe1RyFHeI", ""));
        homeCatListItem1.add(new CategoryItem(2, "Blue", "https://i.picsum.photos/id/1002/200/300.jpg?blur=2&hmac=7njXUCq1q47pq_1dmmaMwTah4tJ_OibEQvcj5zitOgQ", ""));
        homeCatListItem1.add(new CategoryItem(3, "Waterfall", "https://i.picsum.photos/id/573/200/300.webp?hmac=cxWLyEoewFkRk7Fwvh_2y4HZ0-G3uN5ZWmSPxYCnw4Y", ""));
        homeCatListItem1.add(new CategoryItem(4, "Tunnel", "https://i.picsum.photos/id/772/200/300.webp?hmac=x9IAI0zbgNp2riZVS8FIeQe6lFCVN31AZY201ZbM57A", ""));
        homeCatListItem1.add(new CategoryItem(5, "Compasses", "https://i.picsum.photos/id/527/200/300.webp?hmac=xY1wAkXd2cT7PUgSmkBK45Bs9FwILbSm_SzIqKf-7yY", ""));

        allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1, "Watch next TV and movies", homeCatListItem1));
        allCategoryList.add(new AllCategory(2, "Movies in Turkey", homeCatListItem2));
        allCategoryList.add(new AllCategory(3, "Kids and family movies", homeCatListItem3));
        allCategoryList.add(new AllCategory(3, "Amazon original series", homeCatListItem4));
        setMainRecyclerAdapter(allCategoryList);

        getBannerData();
    }

    private void setVpAdapter(List<BannerMovies> homeBannerList) {
        viewPager2 = findViewById(R.id.banner_viewPager2);
        indicatorTab = findViewById(R.id.tab_indicator);
        categoryTab = findViewById(R.id.tabLayout);
        indicatorTab.addTab(indicatorTab.newTab().setText("sign up"));
        VPAdapter vpAdapter = new VPAdapter(homeBannerList, getApplicationContext());
        viewPager2.setAdapter(vpAdapter);
        new TabLayoutMediator(
                indicatorTab,
                viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    }
                }
        ).attach();

        Timer sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(), 4000, 6000);
        new TabLayoutMediator(
                indicatorTab,
                viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    }
                }
        ).attach();
    }

    class AutoSlider extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager2.getCurrentItem() < homeBannerList.size() - 1) {
                        viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);

                    } else {
                        viewPager2.setCurrentItem(0);

                    }


                }
            });

        }

    }

    public void setMainRecyclerAdapter(List<AllCategory> allCategoryList) {
        mainRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, allCategoryList);
        mainRecycler.setAdapter(mainRecyclerAdapter);

    }

    private void setScrollDefaultState() {
        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.scrollTo(0, 0);
        appBarLayout.setExpanded(true);

    }
    private void getBannerData (){
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(RetrofitClient.getRetrofitClient().getAllBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<BannerMovies>>() {
                    private Object BannerMovies;

                    @Override
                    public void onNext(@NonNull List<BannerMovies> list) {
                        Toast.makeText(getApplicationContext(), "hello"+BannerMovies, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("bannerData",""+e);

                    }

                    @Override
                    public void onComplete() {

                    }
                })

        );

    }
}















