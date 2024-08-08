package com.kitri.bark_meow_party_server.test;

import com.kitri.bark_meow_party_server.controller.FeedController;
import com.kitri.bark_meow_party_server.domain.Feed;
import com.kitri.bark_meow_party_server.dto.FeedDetailDTO;
import com.kitri.bark_meow_party_server.dto.FeedWithUserDTO;
import com.kitri.bark_meow_party_server.mapper.FeedHashtagMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@Transactional
public class FeedTest {
    @Autowired
    private FeedController feedController;
    @Autowired
    FeedHashtagMapper feedHashtagMapper;
    @Test
    public void testFeedDetailDTO() {
        // Feed 객체 생성
        Feed feed = new Feed();
        feed.setId(69L);
        feed.setUserId(6L);
        feed.setImage("image_url");
        feed.setContent("Feed content");
        feed.setAnimal("Dog");
        feed.setCreatedAt(LocalDateTime.now());

        // FeedWithUserDTO 객체 생성
    FeedWithUserDTO feedWithUserDTO = new FeedWithUserDTO(
         feed.getId(),
         feed.getUserId(),
         feed.getImage(),
         feed.getContent(),
         feed.getAnimal(),
         feed.getCreatedAt()
    );

    // FeedDetailDTO 객체 생성 및 설정
    FeedDetailDTO feedDetailDTO = new FeedDetailDTO();
    feedDetailDTO.setFeedWithUser(feedWithUserDTO);
    feedDetailDTO.setLikeCount(10);
    feedDetailDTO.setLiked(true);
    feedDetailDTO.setFeedHashtags(new ArrayList<>());

    // Assertions 또는 추가 테스트 코드
}

@Test
void createFeed() throws Exception {
    // 인증 객체 설정 (테스트 사용자)
    Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", Collections.emptyList());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Feed 객체 생성 및 설정
    Feed feed = new Feed();
    feed.setId(1L); // ID는 보통 데이터베이스에서 자동 생성되므로 필요에 따라 설정
    feed.setImage("Sample Image");
    feed.setContent("Sample Content");
    feed.setAnimal("Sample Animal");
    feed.setCreatedAt(LocalDateTime.now());

    // Feed 객체를 FeedDetailDTO로 변환
    FeedWithUserDTO feedWithUserDTO = new FeedWithUserDTO(
            feed.getId(),
            feed.getUserId(),
            feed.getImage(),
            feed.getContent(),
            feed.getAnimal(),
            feed.getCreatedAt()
    );

    // FeedDetailDTO 객체 생성 및 설정
    FeedDetailDTO feedDetailDTO = new FeedDetailDTO();
    feedDetailDTO.setFeedWithUser(feedWithUserDTO);
    feedDetailDTO.setLikeCount(0); // 기본값
    feedDetailDTO.setLiked(false); // 기본값
    feedDetailDTO.setFeedHashtags(new ArrayList<>()); // 기본값

    // FeedDetailDTO 저장
    feedController.save(feedDetailDTO);

    // 저장된 Feed 객체를 검증
    List<FeedDetailDTO> feedDetails = feedController.getFeeds();
    assertThat(feedDetails).isNotNull();
    assertThat(feedDetails).isNotEmpty();

    // 추가 검증: 저장된 Feed 객체가 목록에 포함되어 있는지 확인
    FeedDetailDTO savedFeedDetail = feedDetails.stream()
            .filter(f -> f.getFeedWithUser().getContent().equals("Sample Content"))
            .findFirst()
            .orElse(null);

    assertThat(savedFeedDetail).isNotNull();
    assertThat(savedFeedDetail.getFeedWithUser().getImage()).isEqualTo("Sample Image");
    assertThat(savedFeedDetail.getFeedWithUser().getAnimal()).isEqualTo("Sample Animal");
}

//    @Test
//    void update() throws Exception {
//        // Authenticate a user manually
//        Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", Collections.emptyList());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Create and save a new feed to get its ID
//        Feed feed = new Feed();
//        feed.setImage("Sample Image");
//        feed.setContent("Sample Content");
//        feed.setAnimal("Sample Animal");
//
//        // Save the feed and obtain its ID
//        Feed savedFeed = feedController.save(feed); // assuming save returns the saved feed with ID
//        Long feedId = savedFeed.getId();
//
//        // Update the feed
//        feed.setId(feedId); // Set the ID for the update
//        feed.setContent("Updated Content");
//        feedController.update(feedId, feed);
//
//        // Fetch the updated feeds and verify the update
//        List<FeedDetailDTO> feeds = feedController.getFeeds();
//
//        // Ensure that we are checking the content inside FeedWithUserDTO
//        assertThat(feeds)
//                .isNotEmpty()
//                .extracting("feedWithUser") // Extract FeedWithUserDTO
//                .extracting("content")      // Extract content from FeedWithUserDTO
//                .contains("Updated Content");
//    }
@Test
void update() throws Exception {
    // 사용자 인증 설정
    Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", Collections.emptyList());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // 새 피드를 생성
    FeedDetailDTO feedDetailDTO = new FeedDetailDTO();
    FeedWithUserDTO feedWithUserDTO = new FeedWithUserDTO();
    feedWithUserDTO.setImage("Sample Image");
    feedWithUserDTO.setContent("Sample Content");
    feedWithUserDTO.setAnimal("Sample Animal");
    feedDetailDTO.setFeedWithUser(feedWithUserDTO);
    feedDetailDTO.setFeedHashtags(new ArrayList<>()); // 빈 해시태그 리스트 초기화

    // 피드를 저장하고 ID를 가져옴
    feedController.save(feedDetailDTO); // 피드를 저장하고 응답을 검증
    Long feedId = feedWithUserDTO.getId(); // ID를 업데이트된 후에 가져와야 합니다.

    // 피드를 업데이트
    feedDetailDTO.getFeedWithUser().setId(feedId);
    feedDetailDTO.getFeedWithUser().setContent("Updated Content");
    feedController.update(feedId, feedDetailDTO);

    // 업데이트된 피드를 가져와서 검증
    List<FeedDetailDTO> feeds = feedController.getFeeds();

    // FeedDetailDTO 리스트에서 업데이트된 내용을 확인
    assertThat(feeds)
            .isNotEmpty()
            .extracting("feedWithUser") // FeedWithUserDTO 추출
            .extracting("content")      // FeedWithUserDTO에서 content 추출
            .contains("Updated Content");
}

//    @Test
//    void delete() throws Exception {
//        Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", Collections.emptyList());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        Feed feed = new Feed();
//        feed.setImage("Sample Image");
//        feed.setContent("Sample Content");
//        feed.setAnimal("Sample Animal");
//        Feed savedFeed = feedController.save(feed); // assuming save returns the saved feed with ID
//        Long feedId = savedFeed.getId();
//        feedController.delete(feedId);
//    }
@Test
void delete() throws Exception {
    // 인증 컨텍스트 설정
    Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", Collections.emptyList());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // FeedDetailDTO 객체 생성
    FeedDetailDTO feedDetailDTO = new FeedDetailDTO();
    FeedWithUserDTO feedWithUser = new FeedWithUserDTO();
    feedWithUser.setUserId(1L); // 예시 사용자 ID
    feedWithUser.setImage("Sample Image");
    feedWithUser.setContent("Sample Content");
    feedWithUser.setAnimal("Sample Animal");
    feedDetailDTO.setFeedWithUser(feedWithUser);
    feedDetailDTO.setLikeCount(0);
    feedDetailDTO.setLiked(false);
    feedDetailDTO.setFeedHashtags(Collections.emptyList());

    // 피드 저장
    ResponseEntity<?> response = feedController.save(feedDetailDTO);
    assertEquals("Feed 등록 완료", response.getBody()); // 응답 본문이 "Feed 등록 완료"인지 확인

    // 피드 저장 후 ID를 가져오는 로직 필요 (실제 구현에 맞게 ID를 설정)
    // 예를 들어, ID를 하드코딩하거나 실제로 저장된 피드를 조회하여 ID를 가져옵니다.
    Long feedId = feedWithUser.getId(); // 실제로는 저장 후 ID를 가져와야 함

    // Feed 삭제
    feedController.delete(feedId);

    // 삭제 확인
    try {
        feedController.get(feedId); // get 메서드로 Feed를 조회해본다.
        fail("Feed가 삭제되지 않았습니다.");
    } catch (Exception e) {
        // 예상되는 결과: Feed가 존재하지 않아야 함
    }
}




}
