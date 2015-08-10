/*
 * Copyright 2014-2015. Adaptive.me.
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

package me.adaptive.core.metrics;

import me.adaptive.core.data.domain.MetricBuildEntity;
import me.adaptive.core.data.domain.MetricServerEntity;
import me.adaptive.core.data.repo.MetricBuildRepository;
import me.adaptive.core.data.repo.MetricServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private MetricBuildRepository metricBuildRepository;
    @Autowired
    private MetricServerRepository metricServerRepository;

    @JmsListener(destination = "adaptive.metrics.queue.build")
    public void processBuildMetrics(MetricBuildEntity metric) {

        metricBuildRepository.save(metric);
    }

    @JmsListener(destination = "adaptive.metrics.queue.server")
    public void processServerMetrics(MetricServerEntity metric) {

        metricServerRepository.save(metric);
    }
}
