package com.pubnub.api.endpoints.channel_groups;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.pubnub.api.core.PubnubException;
import com.pubnub.api.endpoints.TestHarness;
import org.junit.Before;
import org.junit.Rule;

import java.io.IOException;
import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertThat;

public class RemoveChannelChannelGroupEndpointTest extends TestHarness {
    private RemoveChannelChannelGroup partialRemoveChannelChannelGroup;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Before
    public void beforeEach() throws IOException {
        partialRemoveChannelChannelGroup = this.createPubNubInstance(8080).removeChannelsFromChannelGroup();
    }

    @org.junit.Test
    public void testSyncSuccess() throws IOException, PubnubException, InterruptedException {
        stubFor(get(urlPathEqualTo("/v2/channel-registration/sub-key/mySubscribeKey/channel-group/groupA"))
                .willReturn(aResponse().withBody("{\"status\": 200, \"message\": \"OK\", \"payload\": {}, \"service\": \"ChannelGroups\"}")));

        boolean response = partialRemoveChannelChannelGroup.channelGroup("groupA").channels(Arrays.asList("ch1", "ch2")).sync();
        assertThat(response, org.hamcrest.Matchers.equalTo(true));
    }

}
