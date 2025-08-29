package com.backendapi.simpletourpackageapi.service.Impl;

import com.backendapi.simpletourpackageapi.dtos.TourRequest;
import com.backendapi.simpletourpackageapi.dtos.TourResponse;
import com.backendapi.simpletourpackageapi.model.Tour;
import com.backendapi.simpletourpackageapi.repository.TourRepository;
import com.backendapi.simpletourpackageapi.service.TourServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TourServiceImpl implements TourServiceInterface {

    private final TourRepository tourRepository;

    private static final Pattern DURATION = Pattern.compile("^(\\d{1,3})Days/(\\d{1,3})Nights$");
    private static final Pattern MONEY = Pattern.compile("^\\$(\\d{1,9}(?:\\.\\d{1,2})?)$");
    private static final Pattern PERCENT = Pattern.compile("^(100|[1-9]?\\d)%$");

    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }


    @Transactional
    @Override
    public TourResponse create(TourRequest request) {

        Matcher m = DURATION.matcher(request.getDuration());
        if (!m.matches()) throw new IllegalArgumentException("Invalid duration format");
        int days = Integer.parseInt(m.group(1));
        int night = Integer.parseInt(m.group(2));

        Matcher moneyMatcher = MONEY.matcher(request.getActualPrice());
        if (!moneyMatcher.matches()) throw new IllegalArgumentException("Invalid Money format");
        BigDecimal actual = new BigDecimal(moneyMatcher.group(1)).setScale(2, RoundingMode.HALF_UP);
        Matcher pctMatcher = PERCENT.matcher(request.getDiscountInPercentage());
        if (!pctMatcher.matches()) throw new IllegalArgumentException("Invalid percent format");
        int pct = Integer.parseInt(pctMatcher.group(1));

        BigDecimal discounted = actual.subtract(actual.multiply(BigDecimal.valueOf(pct)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));

        Tour t = Tour.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .imageUrl(request.getImage())
                .durationDays(days)
                .durationNights(night)
                .actualPrice(actual)
                .discountPercent(pct)
                .discountedPrice(discounted)
                .currency("USD")
                .build();

        t = tourRepository.save(t);

        return new TourResponse(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getImageUrl(),
                t.getDurationDays() + "Days/" + t.getDurationNights() + "Nights",
                "$" + t.getActualPrice(),
                t.getDiscountPercent() + "%",
                "$" + t.getDiscountedPrice(),
                t.getCurrency(),
                t.getCreatedAt().toString()
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TourResponse> findAll(Pageable pageable) {

        Page<Tour> page = tourRepository.findAll(Pageable.unpaged());
        List<TourResponse> tourResponseList = new ArrayList<>();
        for (Tour t : page.getContent()) {
            TourResponse tr = new TourResponse();
            tr.setId(t.getId());
            tr.setTitle(t.getTitle());
            tr.setDescription(t.getDescription());
            tr.setImage(t.getImageUrl());
            tr.setDuration(t.getDurationDays() + "Days/" + t.getDurationNights() + "Nights");
            tr.setActualPrice("$" + t.getActualPrice());
            tr.setDiscountInPercentage(t.getDiscountPercent() + "%");
            tr.setDiscountedPrice("$" + t.getDiscountedPrice());
            tr.setCurrency(t.getCurrency());
            tr.setCreatedAt(t.getCreatedAt().toString());

            tourResponseList.add(tr);
        }
        return new PageImpl<>(tourResponseList, pageable, page.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public TourResponse findById(Long id) {
        Tour t = tourRepository.findById(id).orElseThrow(() -> new RuntimeException("Tour with id=" + id + " not found"));
        return new TourResponse(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getImageUrl(),
                t.getDurationDays() + "Days/" + t.getDurationNights() + "Nights",
                "$" + t.getActualPrice(),
                t.getDiscountPercent() + "%",
                "$" + t.getDiscountedPrice(),
                t.getCurrency(),
                t.getCreatedAt().toString()
        );
    }
    }

