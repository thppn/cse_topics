package reporting;

public enum ResultReporterGenerator {
	HTML{
		public IResultReporter getType() {
			return new HtmlReporter();
		}
	}, MD{
		public IResultReporter getType() {
			return new MarkdownReporter();
		}
	}, TXT{
		public IResultReporter getType() {
			return new TxtReporter();
		}
	};
	
	public abstract IResultReporter getType();
}
