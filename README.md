doc-jockey
==========

One doc-jockey spec = Specification Document = Acceptance Test = Help Document = Regression Test

Doc-jockey is a departure from previous **readable test frameworks**. Instead of using an HTML document as the source of the test, doc-jockey uses plain Scala code. This can be edited by a programmer in the normal way, or by a non-programmer using a WYSIWYG editing tool (as yet unbuilt).

> **Readable test frameworks** could just as easily be called **executable documentation**, **specification by example**, **living documentation**, etc. The canonical example is the Fit Framework, a Java open-source project.

Please expect:
* Test output directories match the package structure
* One spec class = one web page
* Multiples tests in a spec = multiple tests on a web page

I need to build:
* An index page for all your test results
