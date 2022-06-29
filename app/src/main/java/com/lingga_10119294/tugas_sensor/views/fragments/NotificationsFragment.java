package com.lingga_10119294.tugas_sensor.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.lingga_10119294.tugas_sensor.R;
import com.lingga_10119294.tugas_sensor.adapter.AboutPageAdapter;
import com.lingga_10119294.tugas_sensor.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {
    /*
     * NIM : 10119294
     * NAMA : Lingga Juliansyah
     * Kelas : IF-7
     * */
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ViewPager viewPager = (ViewPager) binding.viewpager;
        viewPager.setAdapter(new AboutPageAdapter(getContext()));
//        final TextView textView = binding.textNotifications;
//        textView.setText("NOTIF PAGE");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}