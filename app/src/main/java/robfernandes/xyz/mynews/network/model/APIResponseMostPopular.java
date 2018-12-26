package robfernandes.xyz.mynews.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Roberto Fernandes on 15/12/2018.
 */
@SuppressWarnings("unused")
public class APIResponseMostPopular {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public class Result {

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("adx_keywords")
        @Expose
        private String adxKeywords;
        @SerializedName("column")
        @Expose
        private String column;
        @SerializedName("section")
        @Expose
        private String section;
        @SerializedName("byline")
        @Expose
        private String byline;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("abstract")
        @Expose
        private String _abstract;
        @SerializedName("published_date")
        @Expose
        private String publishedDate;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("id")
        @Expose
        private Long id;
        @SerializedName("asset_id")
        @Expose
        private Long assetId;
        @SerializedName("views")
        @Expose
        private Integer views;
        @SerializedName("media")
        @Expose
        private List<Medium> media = null;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAdxKeywords() {
            return adxKeywords;
        }

        public void setAdxKeywords(String adxKeywords) {
            this.adxKeywords = adxKeywords;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getSection() {
            return section;
        }

        public void setSection(String section) {
            this.section = section;
        }

        public String getByline() {
            return byline;
        }

        public void setByline(String byline) {
            this.byline = byline;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAbstract() {
            return _abstract;
        }

        public void setAbstract(String _abstract) {
            this._abstract = _abstract;
        }

        public String getPublishedDate() {
            return publishedDate;
        }

        public void setPublishedDate(String publishedDate) {
            this.publishedDate = publishedDate;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getAssetId() {
            return assetId;
        }

        public void setAssetId(Long assetId) {
            this.assetId = assetId;
        }

        public Integer getViews() {
            return views;
        }

        public void setViews(Integer views) {
            this.views = views;
        }

        public List<Medium> getMedia() {
            return media;
        }

        public void setMedia(List<Medium> media) {
            this.media = media;
        }

    }

    public class Medium {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("subtype")
        @Expose
        private String subtype;
        @SerializedName("caption")
        @Expose
        private String caption;
        @SerializedName("copyright")
        @Expose
        private String copyright;
        @SerializedName("approved_for_syndication")
        @Expose
        private Integer approvedForSyndication;
        @SerializedName("media-metadata")
        @Expose
        private List<MediaMetadatum> mediaMetadata = null;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public Integer getApprovedForSyndication() {
            return approvedForSyndication;
        }

        public void setApprovedForSyndication(Integer approvedForSyndication) {
            this.approvedForSyndication = approvedForSyndication;
        }

        public List<MediaMetadatum> getMediaMetadata() {
            return mediaMetadata;
        }

        public void setMediaMetadata(List<MediaMetadatum> mediaMetadata) {
            this.mediaMetadata = mediaMetadata;
        }

    }

    @SuppressWarnings("SpellCheckingInspection")
    public class MediaMetadatum {

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("format")
        @Expose
        private String format;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("width")
        @Expose
        private Integer width;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

    }
}
