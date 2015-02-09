package com.ijiaban.fragment;


/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.fcukgfw.lt.R;
import com.squareup.picasso.Picasso;

/**
 * Fragment implementation created to show a poster inside an ImageView widget.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MoviePosterFragment extends Fragment {

    ImageView thumbnailImageView;

    private String videoPosterThumbnail;
    private String posterTitle;

    /**
     * Override method used to initialize the fragment.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_poster, container, false);
        thumbnailImageView=(ImageView)view.findViewById(R.id.iv_thumbnail);
       
        Picasso.with(getActivity()).load(videoPosterThumbnail).placeholder(R.drawable.xmen_placeholder).into(thumbnailImageView);
        return view;
    }

    /**
     * Show the poster image in the thumbnailImageView widget.
     *
     * @param videoPosterThumbnail
     */
    public void setPoster(String videoPosterThumbnail) {
        this.videoPosterThumbnail = videoPosterThumbnail;
    }

    /**
     * Store the poster title to show it when the thumbanil view is clicked.
     *
     * @param posterTitle
     */
    public void setPosterTitle(String posterTitle) {
        this.posterTitle = posterTitle;
    }

    
}