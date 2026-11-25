# Portfolio Optimizer

A Jupyter notebook that uses PyPortfolioOpt to estimate returns and risk from historical closing prices and allocate whole shares within a budget.

Open the [notebook](portfolio-optimizer.ipynb) in Jupyter with pandas, NumPy, Requests, and PyPortfolioOpt. Supply your own `NYSE_Close.csv` with a `Date` column and one closing-price column per ticker. The original Yahoo Finance company-name lookup no longer works; replace it with a local mapping or return the ticker to try the allocation code offline.

Snapshot of `Elliot-Gross/Portfolio-Optimizer` (private) at commit `64205ee1e2615db1b79ae95733e39f81369a0aa3`, 2021-01-21.
