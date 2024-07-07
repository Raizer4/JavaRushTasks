package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.kitchen.Order;
import jdk.incubator.foreign.Addressable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;

public class AdvertisementManager {

    final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void process() {

        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }


        Collections.sort(selectedAds, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long result = o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying();
                if (result != 0) {
                    return (int) result;
                }
                return (int) ((o1.getAmountPerOneDisplaying() / o1.getDuration()) - (o2.getAmountPerOneDisplaying() / o2.getDuration()));
            }
        });

    }

    public void processVideos() {

        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }

        List<Advertisement> selectedAds = getMaxProfitAds(storage, timeSeconds);

        Collections.sort(selectedAds, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long result = o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying();
                if (result != 0) {
                    return (int) result;
                }
                return (int) ((o1.getAmountPerOneDisplaying() / o1.getDuration()) - (o2.getAmountPerOneDisplaying() / o2.getDuration()));
            }
        });

        for (Advertisement ad : selectedAds) {
            System.out.println(ad); // Выводим информацию о каждом выбранном ролике
            ad.revalidate();
        }

    }


    private List<Advertisement> getMaxProfitAds(List<Advertisement> ads, int remainingTime) {

        if (ads.isEmpty() || remainingTime <= 0) {
            return new ArrayList<>();
        }

        Advertisement currentAd = ads.get(0); // первая реклама

        List<Advertisement> remainingAds = ads.subList(1, ads.size()); // лист из реклам но без первой

        // Если текущий ролик превышает оставшееся время, пропускаем его
        if (currentAd.getDuration() > remainingTime) {
            return getMaxProfitAds(remainingAds, remainingTime);
        }

        // Вариант 1: текущий ролик не включен
        List<Advertisement> withoutCurrent = getMaxProfitAds(remainingAds, remainingTime);

        // Вариант 2: текущий ролик включен
        List<Advertisement> withCurrent = new ArrayList<>();

        withCurrent.add(currentAd);
        withCurrent.addAll(getMaxProfitAds(remainingAds, remainingTime - currentAd.getDuration()));

        // Выбор варианта с максимальной выгодой
        long profitWithoutCurrent = withoutCurrent.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum();
        long profitWithCurrent = withCurrent.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum();

        if (profitWithCurrent > profitWithoutCurrent) {
            return withCurrent;
        } else if (profitWithCurrent == profitWithoutCurrent) {
            int timeWithoutCurrent = withoutCurrent.stream().mapToInt(Advertisement::getDuration).sum();
            int timeWithCurrent = withCurrent.stream().mapToInt(Advertisement::getDuration).sum();

            if (timeWithCurrent > timeWithoutCurrent) {
                return withCurrent;
            } else if (timeWithCurrent == timeWithoutCurrent) {
                return withCurrent.size() < withoutCurrent.size() ? withCurrent : withoutCurrent;
            }
        }

        return withoutCurrent;
    }

}

