package com.example.demo;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WishListServiceTest implements WithAssertions {

    @Mock
    private WishListRepository repository;

    @InjectMocks
    private WishListService underTest;

    @Test
    @DisplayName("should return lists for category")
    void should_return_lists_for_category() {
        // given
        Long categoryId = 1L;
        WishList list = new WishList();
        list.setId(111L);

        doReturn(List.of(list)).when(repository).findByCategoryId(categoryId);

        // when
        var result = underTest.getLists(categoryId);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(111L);
    }

    @Test
    @DisplayName("should create list")
    void should_create_list() {
        // given
        WishList list = new WishList();
        list.setTitle("Birthday");

        doReturn(list).when(repository).save(list);

        // when
        var result = underTest.create(list);

        // then
        assertThat(result.getTitle()).isEqualTo("Birthday");
    }
}
