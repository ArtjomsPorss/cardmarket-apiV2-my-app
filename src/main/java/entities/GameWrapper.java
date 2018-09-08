package entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameWrapper {

    @SerializedName("game")
    @Expose
    private List<Game> game = null;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public List<Game> getGame() {
        return game;
    }

    public void setGame(List<Game> game) {
        this.game = game;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("game", game).append("links", links).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(links).append(game).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GameWrapper) == false) {
            return false;
        }
        GameWrapper rhs = ((GameWrapper) other);
        return new EqualsBuilder().append(links, rhs.links).append(game, rhs.game).isEquals();
    }

}