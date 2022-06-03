package com.alkemy.ong.auth.config.seeder;

import com.alkemy.ong.models.entity.ActivityEntity;
import com.alkemy.ong.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class ActivityDataLoader implements CommandLineRunner {
    @Autowired
    ActivityRepository activityRepository;

    @Override
    public void run(String... args) throws Exception {
        loadActivityData();
    }

    private void loadActivityData() {
        if (activityRepository.count() == 0) {
            ActivityEntity activity1 = new ActivityEntity("content1", "image1", "name1");
            ActivityEntity activity2 = new ActivityEntity("content2", "image2", "name2");
            ActivityEntity activity3 = new ActivityEntity("content3", "image3", "name3");
            ActivityEntity activity4 = new ActivityEntity("content4", "image4", "name4");
            ActivityEntity activity5 = new ActivityEntity("content5", "image5", "name5");

            activityRepository.save(activity1);
            activityRepository.save(activity2);
            activityRepository.save(activity3);
            activityRepository.save(activity4);
            activityRepository.save(activity5);

        }

        System.out.println(activityRepository.count());
    }
}
