package me.vilius.angleshot.feature;

import me.vilius.angleshot.feature.include.MassPingFeature;
import me.vilius.angleshot.feature.include.TestFeature;

import java.util.ArrayList;

/*
 *   Written by yours truly - Nya (Vilius)
 *   Created 2019-09-23
 *   Inspired by Flex Seal™
 */
public class FeatureManager
{
    private ArrayList<Feature> featureList = new ArrayList<>();

    public FeatureManager()
    {
        //Adding features manually, TODO: reflections (?)
        this.featureList.add(new TestFeature());
        this.featureList.add(new MassPingFeature());
    }

    public ArrayList<Feature> getFeatureList() {
        return featureList;
    }
}
