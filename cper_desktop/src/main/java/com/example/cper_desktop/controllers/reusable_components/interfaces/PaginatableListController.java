package com.example.cper_desktop.controllers.reusable_components.interfaces;

public interface PaginatableListController {

    void onPageChanged(int newPage);
    int getTotalPages();
    int getCurrentPage();
}