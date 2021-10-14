package com.fon.footballfantasystatsservice.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fon.footballfantasystatsservice.domain.event.Card;
import com.fon.footballfantasystatsservice.domain.event.Goal;
import com.fon.footballfantasystatsservice.domain.event.MatchEvent;
import com.fon.footballfantasystatsservice.domain.event.MinutesPlayed;
import com.fon.footballfantasystatsservice.domain.event.Substitution;

public class MatchEventDeserializer extends JsonDeserializer<MatchEvent> {

	@Override
	public MatchEvent deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectCodec codec = p.getCodec();
		JsonNode tree = codec.readTree(p);

		if (tree.has("card")) {
			return codec.treeToValue(tree, Card.class);
		}
		if (tree.has("ownGoal")) {
			return codec.treeToValue(tree, Goal.class);
		}
		if (tree.has("minutesPlayed")) {
			return codec.treeToValue(tree, MinutesPlayed.class);
		}
		if (tree.has("inPlayer")) {
			return codec.treeToValue(tree, Substitution.class);
		}

		throw new UnsupportedOperationException("Cannot deserialize to a known type");
	}

}