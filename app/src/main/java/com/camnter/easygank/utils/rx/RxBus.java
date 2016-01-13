/*
 * Copyright 2014 Google Inc. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.camnter.easygank.utils.rx;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Description：RxBus ( from https://gist.github.com/benjchristensen/04eef9ca0851f3a5d7bf )
 * Created by：CaMnter
 * Time：2016-01-12 12:09
 */
public class RxBus {

    public static final RxBus bus = new RxBus();
    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    public static synchronized RxBus getInstance() {
        return bus;
    }

    private RxBus() {
    }

    public void send(RxEvent event) {
        _bus.onNext(event);
    }

    public Observable<Object> toObserverable() {
        return _bus;
    }

    public boolean hasObservers() {
        return _bus.hasObservers();
    }

}