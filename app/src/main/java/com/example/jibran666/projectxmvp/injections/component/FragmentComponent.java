package com.example.jibran666.projectxmvp.injections.component;

import com.example.jibran666.projectxmvp.injections.PerFragment;
import com.example.jibran666.projectxmvp.injections.module.FragmentModule;

import dagger.Subcomponent;


/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

}
