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
class WishCategoryServiceTest implements WithAssertions {

    @Mock
    private WishCategoryRepository repository;

    @InjectMocks
    private WishCategoryService underTest;

    @Test
    @DisplayName("should return all categories")
    void should_return_all_categories() {
        // given
        WishCategory cat = new WishCategory();
        cat.setId(1L);

        doReturn(List.of(cat)).when(repository).findAll();

        // when
        var result = underTest.getAllCategories();

        // then
        assertThat(result).hasSize(1);
    }
}
