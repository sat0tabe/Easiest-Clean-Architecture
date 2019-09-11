﻿using System;
using System.Collections.Generic;

namespace AdventureWorks.Sales.Presenter
{
    public interface IPresenter
    {
        void Report(string filePath, IEnumerable<ProductSales> productSales);
    }
}
