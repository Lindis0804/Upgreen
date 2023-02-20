package com.ldh.upgreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import android.widget.CheckBox;

import com.google.android.material.tabs.TabLayout;
import com.ldh.upgreen.Sign.FieldAdapter;
import com.ldh.upgreen.Sign.FieldClickListener;
import com.ldh.upgreen.Sign.SignViewPagerAdapter;
import com.ldh.upgreen.Utils.Default;
import com.ldh.upgreen.Utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity implements FieldClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SignViewPagerAdapter adapter;
    private CheckBox cbField;
    private RecyclerView rvFields;
    private WebView webView;
    private String webContent = " <h1 style=\"font-size: 20px\">Startup thương mại điện tử Việt tìm cách “lấp khoảng trống” trên thị trường trong năm 2022</h1><br /><img style=\" width: 200px; display: block; margin-left: auto; margin-right: auto; width: 50%; \" src='https://khoinghieptre.vn/wp-content/uploads/2022/03/Startup-thuong-mai-dien-tu-Viet-tim-cach-lap-khoang-trong-tren-thi-truong-768x433.jpg'/><p>Cuối tháng 2, công ty thương mại điện tử xuyên biên giới OpenCommerce Group (OCG) công bố huy động 7 triệu USD trong vòng gọi vốn Series A dẫn dắt bởi startup kỳ lân công nghệ VNG cùng sự tham gia của quỹ đầu tư mạo hiểm Do Ventures. </p> <p> Đặt trụ sở chính tại Hà Nội với văn phòng đại diện ở Thâm Quyến (Trung Quốc) và San Francisco (Hoa Kỳ), OCG cung cấp dịch vụ hỗ trợ trọn gói cho những người bán hàng trực tuyến với chi phí thấp và hạn chế rủi ro. </p> <p> Còn trước đó, vào khoảng giữa tháng 1, Mio, một nền tảng thương mại điện tử qua mạng xã hội của Việt Nam vừa công bố khoản gọi vốn trị giá 8 triệu đô la cho vòng Series A. Thương vụ đầu tư này giúp nâng tổng số vốn mà công ty huy động được kể từ khi thành lập lên 9,1 triệu USD. </p>";
    private ViewPager vpNature;
    private CircleIndicator ciNature;
    private NatureAdapter natureAdapter;
    private ArrayList<Nature> natures = new ArrayList<>(
            Arrays.asList(
                    new Nature(R.drawable.nature1),
                    new Nature(R.drawable.nature2),
                    new Nature(R.drawable.house)
            )
    );
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findId();
        cbField.setText("Hello flutter");
        Utils.setRecyclerView(
                rvFields,
                new FieldAdapter(
                        Default.fields,
                        this
                ),
                new GridLayoutManager(
                        this,
                        2
                )
        );
        webView.getSettings().getJavaScriptEnabled();
        webView.loadData(webContent, "text/html", "UTF-8");
        natureAdapter = new NatureAdapter(this, natures);
        vpNature.setAdapter(natureAdapter);
        ciNature.setViewPager(vpNature);
        natureAdapter.registerDataSetObserver(ciNature.getDataSetObserver());
        //viewpager
        adapter = new SignViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        autoSlide();
    }

    public void findId() {
        rvFields = findViewById(R.id.rvFields);
        cbField = findViewById(R.id.cbField);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        webView = findViewById(R.id.webView);
        vpNature = findViewById(R.id.vpNature);
        ciNature = findViewById(R.id.ciNature);
    }


    @Override
    public void onSelectedField(int i) {

    }

    @Override
    public void onUnSelectedField(int i) {

    }

    public void autoSlide() {
        if (natures == null || natures.isEmpty() || vpNature == null) {
            return;
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int cur = vpNature.getCurrentItem();
                        int totalItem = natures.size()-1;
                        if (cur < totalItem) {
                            cur++;
                            vpNature.setCurrentItem(cur);
                        } else {
                            vpNature.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}