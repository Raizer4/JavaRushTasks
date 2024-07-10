package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;

public class AdvertisementManager {

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        this.totalTimeSecondsLeft = Integer.MAX_VALUE;
        obtainOptimalVideoSet(new ArrayList<Advertisement>(), timeSeconds, 0l);

        VideoSelectedEventDataRow row = new VideoSelectedEventDataRow(optimalVideoSet, maxAmount, timeSeconds - totalTimeSecondsLeft);
        StatisticManager.getInstance().register(row);

        displayAdvertisement();
    }

    //recursy
    private long maxAmount;
    private List<Advertisement> optimalVideoSet;
    private int totalTimeSecondsLeft;

    private void obtainOptimalVideoSet(List<Advertisement> totalList, int currentTimeSecondsLeft, long currentAmount) {
        if (currentTimeSecondsLeft < 0) {
            return;
        } else if (currentAmount > maxAmount
                || currentAmount == maxAmount && (totalTimeSecondsLeft > currentTimeSecondsLeft
                || totalTimeSecondsLeft == currentTimeSecondsLeft && totalList.size() < optimalVideoSet.size())) {
            this.totalTimeSecondsLeft = currentTimeSecondsLeft;
            this.optimalVideoSet = totalList;
            this.maxAmount = currentAmount;
            if (currentTimeSecondsLeft == 0) {
                return;
            }
        }

        ArrayList<Advertisement> tmp = getActualAdvertisements();
        tmp.removeAll(totalList);
        for (Advertisement ad : tmp) {
            if (!ad.isActive()) continue;
            ArrayList<Advertisement> currentList = new ArrayList<>(totalList);
            currentList.add(ad);
            obtainOptimalVideoSet(currentList, currentTimeSecondsLeft - ad.getDuration(), currentAmount + ad.getAmountPerOneDisplaying());
        }
    }

    private ArrayList<Advertisement> getActualAdvertisements() {
        ArrayList<Advertisement> advertisements = new ArrayList<>();
        for (Advertisement ad : storage.list()) {
            if (ad.isActive()) {
                advertisements.add(ad);
            }
        }
        return advertisements;
    }

    private void displayAdvertisement() {
        //TODO displaying
        if (optimalVideoSet == null || optimalVideoSet.isEmpty()) {
            throw new NoVideoAvailableException();
        }

        Collections.sort(optimalVideoSet, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long l = o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying();
                return (int) (l != 0 ? l : o2.getDuration() - o1.getDuration());
            }
        });

        for (Advertisement ad : optimalVideoSet) {
            displayInPlayer(ad);
            ad.revalidate();
        }
    }

    private void displayInPlayer(Advertisement advertisement) {
        //TODO get Player instance and display content
        System.out.println(advertisement.getName() + " is displaying... " + advertisement.getAmountPerOneDisplaying() +
                ", " + (1000 * advertisement.getAmountPerOneDisplaying() / advertisement.getDuration()));
    }

    /*public void processVideos() {

        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }


        List<Advertisement> selectedAds = getMaxProfitAds(storage.list(), timeSeconds);

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

        int getAmount = 0;

        for (Advertisement temp : selectedAds){
            getAmount += temp.getAmountPerOneDisplaying();
        }

        VideoSelectedEventDataRow videoSelected = new VideoSelectedEventDataRow(selectedAds,getAmount, timeSeconds);
        StatisticManager.getInstance().register(videoSelected);

        for (Advertisement ad : selectedAds) {
            System.out.println(ad.getName() + " is displaying... " + ad.getAmountPerOneDisplaying() + ", "
                    + (ad.getAmountPerOneDisplaying() * 1000 / ad.getDuration()));
            ad.revalidate();
        }



    }
    private List<Advertisement> getMaxProfitAds(List<Advertisement> ads, int remainingTime) {

        if (ads.isEmpty() || remainingTime <= 0) {
            return new ArrayList<>();
        }

        Advertisement currentAd = ads.get(0);

        List<Advertisement> remainingAds = ads.subList(1, ads.size());


        if (currentAd.getDuration() > remainingTime || currentAd.getHits() <= 0) {
            return getMaxProfitAds(remainingAds, remainingTime);
        }


        List<Advertisement> withoutCurrent = getMaxProfitAds(remainingAds, remainingTime);


        List<Advertisement> withCurrent = new ArrayList<>();

        withCurrent.add(currentAd);
        withCurrent.addAll(getMaxProfitAds(remainingAds, remainingTime - currentAd.getDuration()));


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
    }*/

}

