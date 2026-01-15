package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")  // ❗ deaktiviert initCategories
@WebMvcTest(WishEntryController.class)
class WishEntryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishCategoryService categoryService;

    @MockBean
    private WishListService listService;

    @MockBean
    private WishService entryService;

    private final ObjectMapper mapper = new ObjectMapper();

    // -------------------------------------------------------------------------
    @Test
    @DisplayName("GET /api/categories returns all categories")
    void should_return_all_categories() throws Exception {
        var categories = List.of(
                createCategory(1L, "birthday", "Birthday"),
                createCategory(2L, "christmas", "Christmas")
        );

        doReturn(categories).when(categoryService).getAllCategories();

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].key").value("birthday"));
    }

    // -------------------------------------------------------------------------
    @Test
    @DisplayName("GET /api/categories/{id}/lists returns lists")
    void should_return_lists_for_category() throws Exception {
        var lists = List.of(
                createList(1L, 1L, "Geschenke"),
                createList(2L, 1L, "Wünsche")
        );

        doReturn(lists).when(listService).getLists(1L);

        mockMvc.perform(get("/api/categories/1/lists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("Geschenke"));
    }

    // -------------------------------------------------------------------------
    @Test
    @DisplayName("POST /api/categories/{id}/lists creates a new list")
    void should_create_list() throws Exception {
        WishList input = createList(null, null, "Neue Liste");
        WishList saved = createList(10L, 1L, "Neue Liste");

        doReturn(saved).when(listService).create(any());

        mockMvc.perform(
                        post("/api/categories/1/lists")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(input))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.categoryId").value(1));
    }

    // -------------------------------------------------------------------------
    @Test
    @DisplayName("GET /api/lists/{id}/wishes returns wishes")
    void should_return_wishes_for_list() throws Exception {
        var wishes = List.of(
                createWish(1L, 5L, "Laptop"),
                createWish(2L, 5L, "Kopfhörer")
        );

        doReturn(wishes).when(entryService).getAllForList(5L);

        mockMvc.perform(get("/api/lists/5/wishes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("Laptop"));
    }

    // -------------------------------------------------------------------------
    @Test
    @DisplayName("POST /api/lists/{id}/wishes creates a new wish")
    void should_create_wish() throws Exception {
        WishEntry input = createWish(null, null, "PlayStation 5");
        WishEntry saved = createWish(44L, 3L, "PlayStation 5");

        doReturn(saved).when(entryService).save(any());

        mockMvc.perform(
                        post("/api/lists/3/wishes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(input))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(44))
                .andExpect(jsonPath("$.listId").value(3));
    }

    // -------------------------------------------------------------------------
    @Test
    @DisplayName("PUT /wishes/{id} updates an existing wish")
    void should_update_wish() throws Exception {
        WishEntry updated = createWish(null, 9L, "Buch");
        WishEntry saved = createWish(10L, 9L, "Buch");

        doReturn(saved).when(entryService).update(eq(10L), any());

        mockMvc.perform(
                        put("/api/wishes/10")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(updated))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Buch"));
    }

    // -------------------------------------------------------------------------
    @Test
    @DisplayName("DELETE /wishes/{id} deletes a wish")
    void should_delete_wish() throws Exception {
        mockMvc.perform(delete("/api/wishes/77"))
                .andExpect(status().isOk());

        verify(entryService).delete(77L);
    }

    // -------------------------------------------------------------------------
    // Helper methods
    private WishCategory createCategory(Long id, String key, String label) {
        WishCategory c = new WishCategory();
        c.setId(id);
        c.setKey(key);
        c.setLabel(label);
        return c;
    }

    private WishList createList(Long id, Long catId, String title) {
        WishList l = new WishList();
        l.setId(id);
        l.setCategoryId(catId);
        l.setTitle(title);
        return l;
    }

    private WishEntry createWish(Long id, Long listId, String title) {
        WishEntry w = new WishEntry();
        w.setId(id);
        w.setListId(listId);
        w.setTitle(title);
        return w;
    }
}
