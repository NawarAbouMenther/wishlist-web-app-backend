package com.example.demo;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WishServiceTest implements WithAssertions {

    @Mock
    private WishEntryRepository repository;

    @InjectMocks
    private WishService underTest;

    @Test
    @DisplayName("should return wishes for given list")
    void should_return_wishes_for_list() {
        // given
        Long listId = 10L;
        WishEntry entry = new WishEntry();
        entry.setId(1L);

        doReturn(List.of(entry)).when(repository).findByListId(listId);

        // when
        var result = underTest.getAllForList(listId);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("should save wish")
    void should_save_wish() {
        // given
        WishEntry entry = new WishEntry();
        entry.setTitle("Laptop");

        doReturn(entry).when(repository).save(entry);

        // when
        WishEntry result = underTest.save(entry);

        // then
        assertThat(result.getTitle()).isEqualTo("Laptop");
    }

    @Test
    @DisplayName("should delete wish")
    void should_delete_wish() {
        // given
        Long id = 5L;

        // when
        underTest.delete(id);

        // then
        verify(repository).deleteById(id);
    }

    @Test
    @DisplayName("should update wish")
    void should_update_wish() {
        // given
        Long id = 7L;

        WishEntry existing = new WishEntry();
        existing.setId(id);
        existing.setTitle("Old Title");

        WishEntry updated = new WishEntry();
        updated.setTitle("New Title");

        doReturn(Optional.of(existing)).when(repository).findById(id);
        doReturn(existing).when(repository).save(any(WishEntry.class));

        // when
        WishEntry result = underTest.update(id, updated);

        // then
        assertThat(result.getTitle()).isEqualTo("New Title");
    }
}
