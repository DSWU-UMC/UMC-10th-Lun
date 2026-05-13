package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 리뷰 조회(reviewId 기반)
    Slice<Review> findReviewsByStore_StoreIdAndReviewIdLessThanOrderByReviewIdDesc(Long storeId, long idCursor, PageRequest pageRequest);


    // 리뷰 조회(rating 기반)
    @Query("""
        SELECT r
        FROM Review r
        WHERE r.store.storeId = :storeId
        AND (
            r.rating < :ratingCursor
            OR
            (
                r.rating = :ratingCursor
                AND r.reviewId < :idCursor
            )
        )
        ORDER BY r.rating DESC, r.reviewId DESC
    """)
    Slice<Review> findReviewsByRatingCursor(
            Long storeId,
            Double ratingCursor,
            Long idCursor,
            Pageable pageable
    );


    // 리뷰 조회(커서X, reviewId 기반)
    Slice<Review> findAllByStore_StoreIdOrderByReviewIdDesc(Long storeId, PageRequest pageRequest);


    // 리뷰 조회(커서X, rating 기반)
    @Query("""
        SELECT r
        FROM Review r
        WHERE r.store.storeId = :storeId
        ORDER BY r.rating DESC,
                 r.reviewId DESC
    """)
    Slice<Review>
    findAllByStoreOrderByRatingDescReviewIdDesc(
            Long storeId,
            Pageable pageable
    );
}
